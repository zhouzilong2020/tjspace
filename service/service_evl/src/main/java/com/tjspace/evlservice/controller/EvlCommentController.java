package com.tjspace.evlservice.controller;


import com.tjspace.evlservice.entity.DO.EvlComment;
import com.tjspace.evlservice.entity.VO.NewCommentVO;
import com.tjspace.evlservice.service.EvlCommentService;
import com.tjspace.utils.commonutils.UniformResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@CrossOrigin
@RequestMapping("evlservice")
public class EvlCommentController {

    @Autowired
    private EvlCommentService evlCommentService;

    @ApiOperation("新增评论")
    @PostMapping("courses/{courseId}/comment/")
    public UniformResult addComment(
            @RequestHeader("userId") String userId,
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String courseId,
            @ApiParam(value = "新增评论对象")
            @RequestBody NewCommentVO newCommentVO
    ) {
        EvlComment evlComment = evlCommentService.addComment(userId, courseId, newCommentVO);
        return UniformResult.ok()
                .data("commentId", evlComment.getId())
                .data("createTime", evlComment.getGmtCreate());
    }

    @ApiOperation("分页查询评论")
    @GetMapping("courses/{courseId}/comments/{currentPage}")
    public UniformResult getCommentsByOrder(
            @ApiParam(value = "预期页面索引(1-based)", required = true)
            @PathVariable Integer currentPage,
            @ApiParam(value = "每页条目数", required = true)
            @RequestParam Integer limit,
            @RequestHeader("userId") String userId,
            @ApiParam(value = "课程ID")
            @PathVariable String courseId,
            @ApiParam(value = "排序方式", allowableValues = "{desc, asc}")
            @RequestParam(required = false) String orderBy,
            @ApiParam(value = "排序规则", allowableValues = "{commentId, userId, teachingScore, contentScore, gradingScore, workloadScore, totScore, positiveCount, negativeCount, differentCount, totCount, createTime, latestTime")
            @RequestParam(required = false) String... sort
    ) {
        Map<String, Object> commentsInfo = evlCommentService.getCommentsByOrder(currentPage, limit, courseId, userId, orderBy, sort);
        return UniformResult.ok().data(commentsInfo);
    }

    @ApiOperation("分页查询个人评价")
    @GetMapping("personal/comments/{currentPage}")
    public UniformResult getMyComments(
            @ApiParam(value = "预期页面索引(1-based)", required = true)
            @PathVariable Integer currentPage,
            @ApiParam(value = "每页条目数", required = true)
            @RequestParam Integer limit,
            @RequestHeader("userId") String userId,
            @ApiParam(value = "查询评价字段", allowableValues = "{postId, title, content, userId, positiveCount, negativeCount, replyCount, createTime, latestTime")
            @RequestParam(required = false) String[] attributes
    ) {
        Map<String, Object> myComments = evlCommentService.getMyComments(currentPage, limit, userId, attributes);
        return UniformResult.ok().data(myComments);
    }

    @ApiOperation("删除评论")
    @DeleteMapping("comments/{commentId}")
    public UniformResult removeComment(
            @RequestHeader("userId") String userId,
            @ApiParam(value = "评论ID", required = true)
            @PathVariable String commentId
    ) {
        evlCommentService.removeComment(userId, commentId);
        return UniformResult.noContent().message("删除评论成功");
    }
}

