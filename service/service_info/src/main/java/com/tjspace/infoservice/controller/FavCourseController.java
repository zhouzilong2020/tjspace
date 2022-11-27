package com.tjspace.infoservice.controller;

import com.tjspace.infoservice.service.FavCourseService;
import com.tjspace.utils.commonutils.UniformResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/infoservice")
public class FavCourseController {
    @Autowired
    private FavCourseService favCourseService;

    @ApiOperation("更新收藏课程")
    @PatchMapping("courses/{courseId}/favorite")
    public UniformResult updateFavoriteCourse(
            @RequestHeader("userId") String userId,
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String courseId
    ) {
        Boolean favorite = favCourseService.addOrUpdateFavoriteCourse(userId, courseId);
        return UniformResult.ok().data("favorite", favorite);
    }
}
