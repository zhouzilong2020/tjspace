package com.tjspace.bbsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjspace.bbsservice.client.InfoClient;
import com.tjspace.bbsservice.entity.DO.BbsPost;
import com.tjspace.bbsservice.entity.DO.BbsReply;
import com.tjspace.bbsservice.entity.VO.NewPostVO;
import com.tjspace.bbsservice.entity.VO.PostVO;
import com.tjspace.bbsservice.entity.VO.PostBriefVO;
import com.tjspace.bbsservice.mapper.BbsPostMapper;
import com.tjspace.bbsservice.service.BbsPostAttitudeService;
import com.tjspace.bbsservice.service.BbsPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjspace.bbsservice.service.BbsReplyService;
import com.tjspace.servicebase.entity.DTO.UserPublicInfoDTO;
import com.tjspace.servicebase.exception.MyException;
import com.tjspace.utils.commonutils.Const;
import com.tjspace.utils.commonutils.ResultCode;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Fu Lin
 * @since 2020-12-5
 */
@Service
public class BbsPostServiceImpl extends ServiceImpl<BbsPostMapper, BbsPost> implements BbsPostService {

    @Autowired
    private BbsPostAttitudeService bbsPostAttitudeService;

    @Autowired
    private BbsReplyService bbsReplyService;

    @Autowired
    private InfoClient infoClient;

    @Override
    public Boolean isExist(String postId) {
        QueryWrapper<BbsPost> wrapper = new QueryWrapper<>();
        wrapper.eq(BbsPost.ID, postId);
        return this.count(wrapper) > 0;
    }

    @Override
    public BbsPost addPost(String userId, NewPostVO newPostVO) {
        BbsPost bbsPost = new BbsPost();
        bbsPost.setUserId(userId);
        bbsPost.setTitle(newPostVO.getTitle());
        bbsPost.setContent(newPostVO.getContent());
        this.save(bbsPost);
        return bbsPost;
    }

    @Override
    public BbsPost getPost(String postId, String... attributes) {
        QueryWrapper<BbsPost> wrapper = new QueryWrapper<>();

        if (!ArrayUtils.isEmpty(attributes)) {
            wrapper.select(attributes);
        }
        wrapper.eq(BbsPost.ID, postId);

        BbsPost bbsPost = this.getOne(wrapper);
        if (bbsPost != null) {
            return bbsPost;
        } else {
            throw new MyException(ResultCode.NOT_FOUND, "帖子不存在");
        }
    }

    @Override
    public PostBriefVO getPostBrief(String userId, String postId) {
        PostBriefVO postBriefVO = new PostBriefVO();

        BbsPost bbsPost = this.getPost(postId, BbsPost.USER_ID,
                BbsPost.TITLE,
                BbsPost.POSITIVE_COUNT,
                BbsPost.NEGATIVE_COUNT);

        BeanUtils.copyProperties(bbsPost, postBriefVO);

        Boolean attitude = bbsPostAttitudeService.getAttitude(userId, postId);
        if (attitude != null) {
            postBriefVO.setPositive(attitude);
            postBriefVO.setNegative(!attitude);
        }

        return postBriefVO;
    }


    @Override
    public Map<String, Object> getPostsByOrder(Integer currentPage, Integer limit, String userId, String orderBy, String... sort) {
        if (orderBy == null) {
            orderBy = Const.DESC;
        } else if (!Const.ASC.equals(orderBy) && !Const.DESC.equals(orderBy)) {
            throw new MyException(ResultCode.BAD_REQUEST, "Undefined Order By:" + orderBy);
        }
        if (!ArrayUtils.isEmpty(sort)) {
            for (int i = 0; i < sort.length; i++) {
                if (PostVO.ATTRIBUTE_MAP.containsKey(sort[i])) {
                    sort[i] = PostVO.ATTRIBUTE_MAP.get(sort[i]);
                } else {
                    throw new MyException(ResultCode.BAD_REQUEST, "Undefined Sort: " + sort[i]);
                }
            }
        }

        Map<String, Object> returnMap = new HashMap<>();
        List<PostVO> postVOList = new ArrayList<>();

        //查询当前页面帖子信息
        QueryWrapper<BbsPost> wrapper = new QueryWrapper<>();
        if (Const.ASC.equals(orderBy)) {
            wrapper.orderByAsc(sort);
        } else {
            wrapper.orderByDesc(sort);
        }

        Page<BbsPost> bbsPostPage = new Page<>(currentPage, limit);
        this.page(bbsPostPage, wrapper);

        if (!bbsPostPage.getRecords().isEmpty()) {
            Set<String> postIdSet = new HashSet<>();
            Set<String> userIdSet = new HashSet<>();

            for (BbsPost bbsPost : bbsPostPage.getRecords()) {
                PostVO post = new PostVO();
                BeanUtils.copyProperties(bbsPost, post);
                postVOList.add(post);

                postIdSet.add(bbsPost.getId());
                userIdSet.add(bbsPost.getUserId());
            }
            //查询当前用户对当前页面帖子的态度
            Map<String, Boolean> attitudeMap = bbsPostAttitudeService.getAttitudes(userId, postIdSet);

            //查询发布帖子的用户昵称
            Map<String, UserPublicInfoDTO> userInfoMap = infoClient.getUsersPublicInfo(userIdSet, UserPublicInfoDTO.NICKNAME);

            for (PostVO post : postVOList) {
                if (attitudeMap.containsKey(post.getId())) {
                    Boolean attitude = attitudeMap.get(post.getId());
                    if (attitude != null) {
                        post.setPositive(attitude);
                        post.setNegative(!attitude);
                    }
                }

                if (userInfoMap.containsKey(post.getUserId())) {
                    BeanUtils.copyProperties(userInfoMap.get(post.getUserId()), post);
                }
            }
        }

        returnMap.put("postVOList", postVOList);
        returnMap.put(Const.CURRENT_PAGE, Math.max(1, bbsPostPage.getCurrent()));
        returnMap.put(Const.TOTAL_PAGE, Math.max(1, bbsPostPage.getPages()));

        return returnMap;
    }

    @Override
    public Map<String, Object> getMyPosts(Integer currentPage, Integer limit, String userId, String... attributes) {
        if (!ArrayUtils.isEmpty(attributes)) {
            for (int i = 0; i < attributes.length; i++) {
                if (BbsPost.ATTRIBUTE_MAP.containsKey(attributes[i])) {
                    attributes[i] = BbsPost.ATTRIBUTE_MAP.get(attributes[i]);
                } else {
                    throw new MyException(ResultCode.BAD_REQUEST, "Undefined Attribute: " + attributes[i]);
                }
            }
        }

        Map<String, Object> returnMap = new HashMap<>();

        QueryWrapper<BbsPost> wrapper = new QueryWrapper<>();
        if (!ArrayUtils.isEmpty(attributes)) {
            wrapper.select(attributes);
        }
        wrapper.eq(BbsPost.USER_ID, userId)
                .orderByDesc(BbsPost.GMT_CREATE);


        Page<BbsPost> bbsPostPage = new Page<>(currentPage, limit);
        this.page(bbsPostPage, wrapper);

        returnMap.put("postList", bbsPostPage.getRecords());
        returnMap.put(Const.CURRENT_PAGE, Math.max(1, bbsPostPage.getCurrent()));
        returnMap.put(Const.TOTAL_PAGE, Math.max(1, bbsPostPage.getPages()));
        return returnMap;
    }

    @Override
    public void increaseReplyCount(String postId) {
        UpdateWrapper<BbsPost> wrapper = new UpdateWrapper<>();
        wrapper.eq(BbsPost.ID, postId)
                .setSql(BbsPost.REPLY_COUNT_UPDATE_SQL + 1);
        this.update(new BbsPost(), wrapper);
    }

    @Override
    public void decreaseReplyCount(String postId, int count) {
        UpdateWrapper<BbsPost> wrapper = new UpdateWrapper<>();
        wrapper.eq(BbsPost.ID, postId)
                .setSql(BbsPost.REPLY_COUNT_UPDATE_SQL + (-count));
        this.update(new BbsPost(), wrapper);
    }

    @Override
    public void updateAttitudeCount(String postId, int posCount, int negCount) {
        UpdateWrapper<BbsPost> wrapper = new UpdateWrapper<>();
        wrapper.eq(BbsPost.ID, postId);
        if (posCount != 0) {
            wrapper.setSql(BbsPost.POSITIVE_COUNT_UPDATE_SQL + posCount);
        }
        if (negCount != 0) {
            wrapper.setSql(BbsPost.NEGATIVE_COUNT_UPDATE_SQL + negCount);
        }
        this.update(new BbsPost(), wrapper);
    }

    @Override
    public void removePost(String userId, String postId) {
        QueryWrapper<BbsPost> wrapper = new QueryWrapper<>();
        wrapper.select(BbsPost.USER_ID).eq(BbsPost.ID,postId);
        BbsPost bbsPost = this.getOne(wrapper);
        if(bbsPost!=null){
            if(!userId.equals(bbsPost.getUserId())){
                throw new MyException(ResultCode.FORBIDDEN,"非发布者或管理员无法删除帖子");
            }
            if (baseMapper.deleteById(postId)==1) {
                bbsReplyService.removeReplies(postId);
                bbsPostAttitudeService.removeAttitude(postId);
            }
        }
    }

}
