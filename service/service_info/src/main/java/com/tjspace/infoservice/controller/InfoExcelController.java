package com.tjspace.infoservice.controller;


import com.alibaba.excel.EasyExcel;
import com.tjspace.infoservice.entity.excel.course.ExcelCourseData;
import com.tjspace.infoservice.listener.CourseExcelListener;
import com.tjspace.infoservice.service.InfoCourseService;
import com.tjspace.infoservice.service.InfoTeacherService;
import com.tjspace.infoservice.service.InfoTeachingService;
import com.tjspace.utils.commonutils.UniformResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 操作上传excel的方法
 *
 * @author zhouzilong
 */
@RestController
@RequestMapping("/infoservice/excel")
public class InfoExcelController {

    @Autowired
    private InfoCourseService courseService;

    @Autowired
    private InfoTeachingService teachesService;

    @Autowired
    private InfoTeacherService teacherService;


    /**
     * 传入4m3上导出表格，根据表格数据导入到teacher、teaches、course三张数据库表中
     *
     * @param file 4m3上生成的一个学期的excel表格
     * @return 统一返回结果
     */
    @PostMapping("upload/{year}/{semester}")
    @ApiOperation("上传4m3上课程信息")
    public UniformResult upload(
            @ApiParam(required = true, value = "上传的excel文件") MultipartFile file,
            @ApiParam(required = true, value = "年份, 格式：YYYY")
            @RequestParam(value = "year", defaultValue = "0") Integer year,
            @ApiParam(required = true, value = "学期 0：春季， 1：夏季，2：秋季")
            @RequestParam(value = "semester", defaultValue = "0") Integer semester
    ) {
        try {
            InputStream in = file.getInputStream();
            // 读取数据
            EasyExcel.read(in, ExcelCourseData.class, new CourseExcelListener(this.courseService, this.teacherService, this.teachesService, year, semester)).sheet().doRead();
            return UniformResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return UniformResult.error();
        }
    }
}
