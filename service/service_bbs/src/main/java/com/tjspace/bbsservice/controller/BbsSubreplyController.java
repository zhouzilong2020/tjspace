package com.tjspace.bbsservice.controller;


import com.tjspace.bbsservice.entity.DO.BbsSubreply;
import com.tjspace.bbsservice.service.BbsSubreplyService;
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
@RequestMapping("bbsservice")
public class BbsSubreplyController {
    @Autowired
    private BbsSubreplyService bbsSubreplyService;

    @ApiOperation("新增一个子回复")
    @PostMapping("replies/{replyId}/subreply")
    public UniformResult addSubreply(
            @RequestHeader("userId") String userId,
            @ApiParam(value = "回复ID", required = true)
            @PathVariable String replyId,
            @ApiParam(value = "子回复内容", required = true)
            @RequestBody String content
    ) {
        BbsSubreply bbsSubreply = bbsSubreplyService.addSubreply(userId, replyId, content);
        return UniformResult.ok()
                .data("subreplyId", bbsSubreply.getId())
                .data("createTime", bbsSubreply.getGmtCreate());
    }

    @ApiOperation("删除某个子回复")
    @DeleteMapping("subreplies/{subreplyId}")
    public UniformResult removeSubreply(
            @RequestHeader("userId") String userId,
            @ApiParam(value = "子回复ID", required = true)
            @PathVariable String subreplyId
    ) {
        bbsSubreplyService.removeSubreply(userId, subreplyId);
        return UniformResult.noContent().message("删除子回复成功");
    }

}

