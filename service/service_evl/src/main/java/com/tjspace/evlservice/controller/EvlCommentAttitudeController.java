package com.tjspace.evlservice.controller;


import com.tjspace.evlservice.service.EvlCommentAttitudeService;
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
 * @author Zilong Zhou
 * @since 2020-11-24
 */
@RestController
@RequestMapping("evlservice")
public class EvlCommentAttitudeController {
    @Autowired
    private EvlCommentAttitudeService evlCommentAttitudeService;

    @ApiOperation("新增或更新对某个评价的态度")
    @PatchMapping("comments/{commentId}/attitude")
    public UniformResult updateAttitude(
            @RequestHeader("userId") String userId,
            @ApiParam(value = "帖子ID", required = true)
            @PathVariable String commentId,
            @ApiParam(value = "态度{false:点踩;true:点赞}", required = true)
            @RequestParam Boolean type
    ) {

        Boolean attitude = evlCommentAttitudeService.addOrUpdateAttitude(userId, commentId, type);
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
