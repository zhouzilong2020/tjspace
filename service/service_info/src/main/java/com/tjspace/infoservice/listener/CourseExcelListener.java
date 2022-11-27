package com.tjspace.infoservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.tjspace.infoservice.entity.DO.InfoCourse;
import com.tjspace.infoservice.entity.DO.InfoTeacher;
import com.tjspace.infoservice.entity.DO.InfoTeaching;
import com.tjspace.infoservice.entity.excel.course.ExcelCourseData;
import com.tjspace.infoservice.service.InfoCourseService;
import com.tjspace.infoservice.service.InfoTeacherService;
import com.tjspace.infoservice.service.InfoTeachingService;
import com.tjspace.servicebase.exception.MyException;
import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * 实现数据的读取
 * 需要自己读取，
 *
 * @author zhouzilong
 */
public class CourseExcelListener extends AnalysisEventListener<ExcelCourseData> {

    // 因为该监听器不是spring管理， 不能注入其他内容
    // 不能实现数据库直接操作
    // 需要手动搞进去

    public InfoCourseService courseService;
    public InfoTeacherService teacherService;
    public InfoTeachingService teachingService;
    public Integer year;
    public Integer semester;

    private List<InfoCourse> cellCourse;
    private List<InfoTeacher> cellTeacher;
    private List<ExcelCourseData> history;
    private List<InfoTeaching> cellTeaching;


    /**
     * 最大批处理上限
     */
    private final Integer BATCH_NUMBER = 200;
    private Integer counter = 0;

    public CourseExcelListener() {
    }

    public CourseExcelListener(
            InfoCourseService courseService,
            InfoTeacherService teacherService,
            InfoTeachingService teachesService,
            Integer year,
            Integer semester) {
        cellCourse = new ArrayList<>(BATCH_NUMBER);
        cellTeacher = new ArrayList<>(BATCH_NUMBER);
        cellTeaching = new ArrayList<>(BATCH_NUMBER);
        history = new ArrayList<>(BATCH_NUMBER);
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.teachingService = teachesService;
        this.year = year;
        this.semester = semester;
    }


    private <T> void save(List<T> cellList, T temp, ExcelCourseData cellData) {
        BeanUtils.copyProperties(cellData, temp);
        cellList.add(temp);
    }

    private void save(List<InfoTeacher> cellList, InfoTeacher temp, ExcelCourseData cellData) {
        temp.setName(cellData.getTeacherName());
        temp.setTitle(cellData.getTeacherTitle());
        cellList.add(temp);

    }

    private void save(List<InfoTeaching> cellList, InfoTeaching temp, ExcelCourseData cellData) {
        temp.setSemester(semester);
        temp.setYear(year);
        BeanUtils.copyProperties(cellData, temp);
        cellList.add(temp);
    }

    /**
     * 需要excel的Entity和数据库Entity合理对应
     *
     * @param cellData excel中读取的一行数据
     */
    @Override
    public void invoke(ExcelCourseData cellData, AnalysisContext analysisContext) {
        if (cellData == null) {
            throw new MyException(20001, "读取文件数据为空");
        }
        // cellData预处理
        cellData.setRemark(cellData.getRemark() + cellData.getOther());
        handleVoidField(cellData);
        if (counter < BATCH_NUMBER) {
            // 缓存
            save(cellCourse, new InfoCourse(), cellData);
            save(cellTeacher, new InfoTeacher(), cellData);
            save(cellTeaching, new InfoTeaching(), cellData);
            save(history, new ExcelCourseData(), cellData);
            counter++;
        } else {
            // 去除teacher重复字段
            cellTeacher = cellTeacher.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(InfoTeacher::getName))), ArrayList::new));

            // 向数据库插入新的教师字段
            String[] teacherNames = new String[cellTeacher.size()];
            for (InfoTeacher teacher : cellTeacher) {
                teacherNames = Strings.addStringToArray(teacherNames, teacher.getName());
            }
            List<InfoTeacher> teacherDB = teacherService.getTeacherByName(teacherNames);

            cellTeacher.removeAll(teacherDB);
            if (cellTeacher.size() > 0) {
                teacherService.saveBatch(cellTeacher);
                teacherDB.addAll(cellTeacher);
            }
            // 建立教师到教师id的映射map
            Map<String, String> teacherToTeacherId = teacherDB.stream().collect(Collectors.toMap(InfoTeacher::getName, InfoTeacher::getId));
            // 查找数据库中是否存在课程，这里是按照officialId查， 可能会多， 但是没有影响
            String[] courseOfficialIds = new String[cellCourse.size()];
            for (InfoCourse course : cellCourse) {
                courseOfficialIds = Strings.addStringToArray(courseOfficialIds, course.getOfficialId());
            }
            List<InfoCourse> courseDB = courseService.getCourseByOfficialId(courseOfficialIds);
            // 将教师信息填充进cellCourse中
            Iterator<ExcelCourseData> excelCourseIterator = history.iterator();
            Iterator<InfoCourse> courseIterator = cellCourse.iterator();
            while (excelCourseIterator.hasNext() && courseIterator.hasNext()) {
                ExcelCourseData excelCourse = excelCourseIterator.next();
                InfoCourse course = courseIterator.next();
                // 将缓存中的course中的teacherId回填
                if (teacherToTeacherId.containsKey(excelCourse.getTeacherName())) {
                    course.setTeacherId(teacherToTeacherId.get(excelCourse.getTeacherName()));
                }
            }
            // 去掉数据库已经存在的
            cellCourse.removeAll(courseDB);
            // 去掉重复的
            cellCourse = cellCourse.stream().collect(Collectors.collectingAndThen(toCollection(() -> new TreeSet<>(comparing(a -> a.getTeacherId() + a.getOfficialId()))), ArrayList::new));
            if (cellCourse.size() > 0) {
                courseService.saveBatch(cellCourse);
                // 此时courseDB具有id
                courseDB.addAll(cellCourse);
            }
            // 建立课程officialId+teacherId到课程id的映射map
            Map<String, String> courseToCourseId = courseDB.stream().collect(Collectors.toMap(key -> key.getTeacherId() + key.getOfficialId(), InfoCourse::getId));
            // 回填teaching中的字段
            excelCourseIterator = history.iterator();
            Iterator<InfoTeaching> teachingIterator = cellTeaching.iterator();
            while (excelCourseIterator.hasNext() && teachingIterator.hasNext()) {
                ExcelCourseData excelCourse = excelCourseIterator.next();
                InfoTeaching teaching = teachingIterator.next();
                // 将缓存中的course中的teacherId回填
                if (teacherToTeacherId.containsKey(excelCourse.getTeacherName())) {
                    teaching.setTeacherId(teacherToTeacherId.get(excelCourse.getTeacherName()));
                }
                if (courseToCourseId.containsKey(teaching.getTeacherId() + excelCourse.getOfficialId())) {
                    teaching.setCourseId(courseToCourseId.get(teaching.getTeacherId() + excelCourse.getOfficialId()));
                }
            }
            // 根据teacherId， courseId，year，semester查找数据库
            List<InfoTeaching> teachingDB = teachingService.selectInfoTeaching(cellTeaching);
            // 删除数据库中已有
            cellTeaching.removeAll(teachingDB);
            // 删除重复
            cellTeaching = cellTeaching.stream().collect(Collectors.collectingAndThen(toCollection(() -> new TreeSet<>(comparing(a -> a.getTeacherId() + a.getCourseId() + a.getSchoolTime() + a.getSemester() + a.getYear()))), ArrayList::new));
            // 保存
            if (cellTeaching.size() > 0) {
                teachingService.saveBatch(cellTeaching);
            }

            history.clear();
            cellCourse.clear();
            cellTeaching.clear();
            cellTeacher.clear();
            counter = 0;
        }
        // TODO 如何向teaching中插入授课字段？
    }


    /**
     * 处理excel中的空字段
     *
     * @param excelCourseData excel数据
     */
    private void handleVoidField(ExcelCourseData excelCourseData) {
        if (excelCourseData.getRemark() == null) {
            excelCourseData.setRemark("null");
        }
        if (excelCourseData.getTeacherTitle() == null) {
            excelCourseData.setTeacherTitle("null");
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

//    public static <C> boolean func(C a, Predicate<C> f) {
//        return f.test(a);
//    }

//    public static void main(String[] args) {
//
//        @Data
//        class C {
//            public String field1;
//            public Integer field2;
//
//
//            C(String a, Integer b) {
//                this.field1 = a;
//                this.field2 = b;
//            }
//        }
//
//        List<C> list = new ArrayList<>(10);
//        list.add(new C("a", 1));
//        list.add(new C("b", 2));
//        list.add(new C("c", 2));
//        list.add(new C("c", 2));
//
//        list = list.stream()
//                .collect(
//                        collectingAndThen(
//                                toCollection(() ->
//                                        new TreeSet<C>(comparing(a
//                                                -> a.getField1() + "-" + a.getField2().toString()))), ArrayList::new));
//
//        System.out.println(
//                func(new C("nihao", 2),
//                        a -> a.getField2().equals(2)));
//    }

}
