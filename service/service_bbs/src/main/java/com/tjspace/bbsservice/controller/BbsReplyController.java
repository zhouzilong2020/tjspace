package com.tjspace.bbsservice.controller;


import com.tjspace.bbsservice.entity.DO.BbsReply;
import com.tjspace.bbsservice.service.BbsReplyService;
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
@RequestMapping("bbsservice")
public class BbsReplyController {
    @Autowired
    private BbsReplyService bbsReplyService;

    @ApiOperation("新增一个回复")
    @PostMapping("posts/{postId}/reply")
    public UniformResult addReply(
            @RequestHeader("userId") String userId,
            @ApiParam(value = "帖子ID", required = true)
            @PathVariable String postId,
            @ApiParam(value = "回复内容", required = true)
            @RequestBody String content
    ) {
        BbsReply bbsReply = bbsReplyService.addReply(userId, postId, content);
        return UniformResult.ok()
                .data("replyId", bbsReply.getId())
                .data("createTime", bbsReply.getGmtCreate());
    }

    @ApiOperation("分页查询某个帖子的回复与子回复")
    @GetMapping("posts/{postId}/replies/{currentPage}")
    public UniformResult getReplies(
            @ApiParam(value = "预期页面索引(1-based)", required = true)
            @PathVariable Integer currentPage,
            @ApiParam(value = "每页条目数", required = true)
            @RequestParam Integer limit,
            @ApiParam(value = "帖子ID", required = true)
            @PathVariable String postId,
            @ApiParam(value = "是否只看楼主")
            @RequestParam(defaultValue = "false", required = false) Boolean onlyPoster
    ) {
        Map<String, Object> replies = bbsReplyService.getReplies(currentPage, limit, postId, onlyPoster);
        return UniformResult.ok().data(replies);
    }

    @ApiOperation("删除某个回复")
    @DeleteMapping("replies/{replyId}")
    public UniformResult removeReply(
            @RequestHeader("userId") String userId,
            @ApiParam(value = "回复ID", required = true)
            @PathVariable String replyId
    ) {
        bbsReplyService.removeReply(userId, replyId);
        return UniformResult.noContent().message("删除回复成功");
    }
}

