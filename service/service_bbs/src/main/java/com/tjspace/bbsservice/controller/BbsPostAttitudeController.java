package com.tjspace.bbsservice.controller;

import com.tjspace.bbsservice.service.BbsPostAttitudeService;
import com.tjspace.utils.commonutils.UniformResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Fu Lin
 * @since 2020-12-5
 */
@RestController
@RequestMapping("bbsservice")
public class BbsPostAttitudeController {
    @Autowired
    private BbsPostAttitudeService bbsPostAttitudeService;


    @ApiOperation("新增或更新对某个帖子的态度")
    @PatchMapping("posts/{postId}/attitude")
    public UniformResult updateAttitude(
            @RequestHeader("userId") String userId,
            @ApiParam(value = "帖子ID", required = true)
            @PathVariable String postId,
            @ApiParam(value = "态度{false:点踩;true:点赞}", required = true)
            @RequestParam Boolean type
    ) {
        Boolean attitude = bbsPostAttitudeService.addOrUpdateAttitude(userId, postId, type);
        boolean positive = false, negative = false;
        if (attitude != null) {
            positive = attitude;
            negative = !attitude;
        }
        return UniformResult.ok()
                .data("positive", positive)
                .data("negative", negative);
    }

}
