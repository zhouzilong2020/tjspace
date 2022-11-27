package com.tjspace.infoservice.controller;


import com.tjspace.infoservice.entity.VO.CourseVO;
import com.tjspace.infoservice.service.InfoCourseService;
import com.tjspace.utils.commonutils.UniformResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
@RestController
@RequestMapping("/infoservice")
public class InfoCourseController {

    @Autowired
    private InfoCourseService infoCourseService;

    @ApiOperation("分页按院系查询课程")
    @GetMapping("/courses/{currentPage}/brief")
    public UniformResult getCoursesByDeptIds(
            @ApiParam(value = "预期页面索引(1-based)", required = true)
            @PathVariable Integer currentPage,
            @ApiParam(value = "每页条目数", required = true)
            @RequestParam Integer limit,
            @ApiParam(value = "院系ID")
            @RequestParam(required = false) String... deptIds
    ) {

        Map<String, Object> courses = infoCourseService.getCoursesByDeptIds(currentPage, limit, deptIds);
        return UniformResult.ok().data(courses);
    }

    @ApiOperation("按ID查询课程")
    @GetMapping("courses/{courseId}")
    public UniformResult getCourse(
            @RequestHeader("userId") String userId,
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String courseId,
            @ApiParam(value = "筛选字段", allowableValues = "{title,credit,period,checkType,officialId,brief,remark,majorName,deptName,teacherName,teacherTitle,teacherDeptName,avgTeachingScore,avgContentScore,avgGradingScore,avg_workload_score,avgTotScore,commentCount}")
            @RequestParam(required = false) String... attributes
    ) {
        CourseVO courseVO = infoCourseService.getCourse(userId, courseId, attributes);
        return UniformResult.ok().data(courseVO);
    }

    @GetMapping("inner/courses/{courseId}/existence")
    public Boolean isExist(
            @ApiParam(value = "课程ID")
            @PathVariable String courseId
    ) {
        return infoCourseService.isExist(courseId);
    }

    @ApiOperation(value = "分页按关键字查询课程")
    @GetMapping("courses/{keywords}/{currentPage}/brief")
    public UniformResult getCoursesByKeywords(
            @ApiParam(value = "预期页面索引(1-based)", required = true)
            @PathVariable Integer currentPage,
            @ApiParam(value = "每页条目数", required = true)
            @RequestParam Integer limit,
            @ApiParam(value = "关键字", required = true)
            @PathVariable String keywords
    ) {
        String[] keywordArray = keywords.split("\\s+");
        Map<String, Object> courses = infoCourseService.getCoursesByKeywords(currentPage, limit, keywordArray);
        return UniformResult.ok().data(courses);
    }

    @ApiOperation("分页查询个人收藏课程")
    @GetMapping("personal/courses/favorite/{currentPage}")
    public UniformResult getMyFavoriteCourses(
            @ApiParam(value = "预期页面索引(1-based)", required = true)
            @PathVariable Integer currentPage,
            @ApiParam(value = "每页条目数", required = true)
            @RequestParam Integer limit,
            @RequestHeader("userId") String userId
    ) {
        Map<String, Object> courses = infoCourseService.getMyFavoriteCourses(currentPage, limit, userId);

        return UniformResult.ok().data(courses);
    }

}

