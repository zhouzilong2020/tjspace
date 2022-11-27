package com.tjspace.bbsservice.controller;


import com.tjspace.bbsservice.entity.DO.BbsPost;
import com.tjspace.bbsservice.entity.VO.NewPostVO;
import com.tjspace.bbsservice.entity.VO.PostBriefVO;
import com.tjspace.bbsservice.service.BbsPostService;
import com.tjspace.utils.commonutils.UniformResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
public class BbsPostController {
    @Autowired
    private BbsPostService bbsPostService;

    @ApiOperation("新增一个帖子")
    @PostMapping("post")
    public UniformResult addPost(
            @ApiParam(value = "新增帖子对象", required = true)
            @RequestBody NewPostVO newPostVO,
            @RequestHeader("userId") String userId
    ) {
        BbsPost bbsPost = bbsPostService.addPost(userId, newPostVO);
        return UniformResult.ok()
                .data("postId", bbsPost.getId())
                .data("createTime", bbsPost.getGmtCreate());
    }

    @ApiOperation("查询某个帖子简略信息")
    @GetMapping("posts/{postId}/brief")
    public UniformResult getPostBriefInfo(
            @ApiParam(value = "帖子ID", required = true)
            @PathVariable String postId,
            @RequestHeader("userId") String userId
    ) {
        PostBriefVO postBriefVO = bbsPostService.getPostBrief(userId, postId);
        return UniformResult.ok().data(BeanMap.create(postBriefVO));
    }

    @ApiOperation("分页查询帖子")
    @GetMapping("posts/{currentPage}")
    public UniformResult getPostsInfo(
            @ApiParam(value = "预期页面索引(1-based)", required = true)
            @PathVariable Integer currentPage,
            @ApiParam(value = "每页条目数", required = true)
            @RequestParam Integer limit,
            @RequestHeader("userId") String userId,
            @ApiParam(value = "排序方式", allowableValues = "{desc, asc}")
            @RequestParam(required = false) String orderBy,
            @ApiParam(value = "排序规则", allowableValues = "{postId, userId, positiveCount, negativeCount, replyCount, createTime, latestTime")
            @RequestParam(required = false) String... sort
    ) {
        Map<String, Object> postsInfo = bbsPostService.getPostsByOrder(currentPage, limit, userId, orderBy, sort);
        return UniformResult.ok().data(postsInfo);
    }

    @ApiOperation("分页查询个人帖子")
    @GetMapping("personal/posts/{currentPage}")
    public UniformResult getMyPostsInfo(
            @ApiParam(value = "预期页面索引(1-based)", required = true)
            @PathVariable Integer currentPage,
            @ApiParam(value = "每页条目数", required = true)
            @RequestParam Integer limit,
            @RequestHeader("userId") String userId,
            @ApiParam(value = "查询字段", allowableValues = "{postId, title, content, userId, positiveCount, negativeCount, replyCount, createTime, latestTime")
            @RequestParam(required = false) String... attributes
    ) {
        Map<String, Object> myPosts = bbsPostService.getMyPosts(currentPage, limit, userId, attributes);
        return UniformResult.ok().data(myPosts);
    }

    @ApiOperation("删除某个帖子")
    @DeleteMapping("posts/{postId}")
    public UniformResult removePost(
            @RequestHeader("userId") String userId,
            @ApiParam(value = "帖子ID", required = true)
            @PathVariable String postId
    ) {
        bbsPostService.removePost(userId, postId);
        return UniformResult.noContent().message("删除帖子成功");
    }
}

