package com.tjspace.evlservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjspace.evlservice.client.InfoClient;
import com.tjspace.evlservice.entity.DO.EvlComment;
import com.tjspace.evlservice.entity.VO.CommentVO;
import com.tjspace.evlservice.entity.VO.NewCommentVO;
import com.tjspace.evlservice.mapper.EvlCommentMapper;
import com.tjspace.evlservice.service.EvlAgvScoreService;
import com.tjspace.evlservice.service.EvlCommentAttitudeService;
import com.tjspace.evlservice.service.EvlCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjspace.servicebase.entity.DTO.UserPublicInfoDTO;
import com.tjspace.servicebase.exception.MyException;
import com.tjspace.utils.commonutils.Const;
import com.tjspace.utils.commonutils.ResultCode;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
@Service
public class EvlCommentServiceImpl extends ServiceImpl<EvlCommentMapper, EvlComment> implements EvlCommentService {

    @Autowired
    private EvlCommentAttitudeService evlCommentAttitudeService;

    @Autowired
    private InfoClient infoClient;

    @Autowired
    private EvlAgvScoreService evlAgvScoreService;

    @Override
    public EvlComment addComment(String userId, String courseId, NewCommentVO newCommentVO) {
        if (!infoClient.isExist(courseId)) {
            throw new MyException(ResultCode.NOT_FOUND, "课程不存在");
        }
        EvlComment evlComment = new EvlComment();
        BeanUtils.copyProperties(newCommentVO, evlComment);
        evlComment.setUserId(userId);
        evlComment.setCourseId(courseId);
        evlComment.setTotScore(BigDecimal.valueOf(1.0 *
                (newCommentVO.getTeachingScore() +
                        newCommentVO.getGradingScore() +
                        newCommentVO.getContentScore() +
                        newCommentVO.getWorkloadScore()) / 4));
        try {
            this.save(evlComment);
            evlAgvScoreService.updateAverageScore(courseId);
            return evlComment;
        } catch (DuplicateKeyException duplicateKeyException) {
            throw new MyException(ResultCode.FORBIDDEN, "您已经对该课程进行过评价");
        }
    }

    @Override
    public Boolean isExist(String commentId) {
        QueryWrapper<EvlComment> wrapper = new QueryWrapper<>();
        wrapper.eq(EvlComment.ID, commentId);
        return this.count(wrapper) == 1;
    }

    @Override
    public Map<String, Object> getCommentsByOrder(Integer currentPage, Integer limit, String courseId, String userId, String orderBy, String... sort) {
        if (orderBy == null) {
            orderBy = Const.DESC;
        } else if (!Const.ASC.equals(orderBy) && !Const.DESC.equals(orderBy)) {
            throw new MyException(ResultCode.BAD_REQUEST, "Undefined Order By:" + orderBy);
        }
        if (!ArrayUtils.isEmpty(sort)) {
            for (int i = 0; i < sort.length; i++) {
                if (CommentVO.ATTRIBUTE_MAP.containsKey(sort[i])) {
                    sort[i] = CommentVO.ATTRIBUTE_MAP.get(sort[i]);
                } else {
                    throw new MyException(ResultCode.BAD_REQUEST, "Undefined Sort: " + sort[i]);
                }
            }
        }

        Map<String, Object> returnMap = new HashMap<>();
        List<CommentVO> commentVOList = new ArrayList<>();

        QueryWrapper<EvlComment> wrapper = new QueryWrapper<>();
        if (Const.ASC.equals(orderBy)) {
            wrapper.orderByAsc(sort);
        } else {
            wrapper.orderByDesc(sort);
        }
        wrapper.eq(EvlComment.COURSE_ID, courseId);

        Page<EvlComment> evlCommentPage = new Page<>(currentPage, limit);
        this.page(evlCommentPage, wrapper);

        if (!evlCommentPage.getRecords().isEmpty()) {
            Set<String> commentIdSet = new HashSet<>();
            Set<String> userIds = new HashSet<>();

            for (EvlComment bbsPost : evlCommentPage.getRecords()) {
                CommentVO post = new CommentVO();
                BeanUtils.copyProperties(bbsPost, post);
                commentVOList.add(post);

                commentIdSet.add(bbsPost.getId());
                userIds.add(bbsPost.getUserId());
            }
            //查询当前用户对当前页面帖子的态度
            Map<String, Boolean> attitudeMap = evlCommentAttitudeService.getAttitudes(userId, commentIdSet);

            //查询发布帖子的用户昵称
            Map<String, UserPublicInfoDTO> userInfoMap = infoClient.getUsersPublicInfo(userIds, UserPublicInfoDTO.NICKNAME);

            for (CommentVO commentVO : commentVOList) {
                if (attitudeMap.containsKey(commentVO.getId())) {
                    Boolean attitude = attitudeMap.get(commentVO.getId());
                    if (attitude != null) {
                        commentVO.setPositive(attitude);
                        commentVO.setNegative(!attitude);
                    }
                }

                if (userInfoMap.containsKey(commentVO.getUserId())) {
                    BeanUtils.copyProperties(userInfoMap.get(commentVO.getUserId()), commentVO);
                }
            }
        }
        returnMap.put("commentList", commentVOList);
        returnMap.put(Const.CURRENT_PAGE, Math.max(1, evlCommentPage.getCurrent()));
        returnMap.put(Const.TOTAL_PAGE, Math.max(1, evlCommentPage.getPages()));
        return returnMap;
    }

    @Override
    public Map<String, Object> getMyComments(Integer currentPage, Integer limit, String userId, String... attributes) {
        if (!ArrayUtils.isEmpty(attributes)) {
            for (int i = 0; i < attributes.length; i++) {
                if (EvlComment.ATTRIBUTE_MAP.containsKey(attributes[i])) {
                    attributes[i] = EvlComment.ATTRIBUTE_MAP.get(attributes[i]);
                } else {
                    throw new MyException(ResultCode.BAD_REQUEST, "Undefined Attribute: " + attributes[i]);
                }
            }
        }

        Map<String, Object> returnMap = new HashMap<>();

        QueryWrapper<EvlComment> wrapper = new QueryWrapper<>();
        if (!ArrayUtils.isEmpty(attributes)) {
            wrapper.select(attributes);
        }
        wrapper.eq(EvlComment.USER_ID, userId)
                .orderByDesc(EvlComment.GMT_CREATE);


        Page<EvlComment> bbsPostPage = new Page<>(currentPage, limit);
        this.page(bbsPostPage, wrapper);

        returnMap.put("commentList", bbsPostPage.getRecords());
        returnMap.put(Const.CURRENT_PAGE, Math.max(1, bbsPostPage.getCurrent()));
        returnMap.put(Const.TOTAL_PAGE, Math.max(1, bbsPostPage.getPages()));
        return returnMap;
    }

    @Override
    public void updateAttitudeCount(String commentId, int posCount, int negCount) {
        UpdateWrapper<EvlComment> wrapper = new UpdateWrapper<>();
        wrapper.eq(EvlComment.ID, commentId);
        if (posCount != 0) {
            wrapper.setSql(EvlComment.POSITIVE_COUNT_UPDATE_SQL + posCount);
        }
        if (negCount != 0) {
            wrapper.setSql(EvlComment.NEGATIVE_COUNT_UPDATE_SQL + negCount);
        }
        if (posCount - negCount != 0) {
            wrapper.setSql(EvlComment.DIFFERENT_COUNT_UPDATE_SQL + (posCount - negCount));
        }
        if (posCount + negCount != 0) {
            wrapper.setSql(EvlComment.TOT_COUNT_UPDATE_SQL + (posCount + negCount));
        }
        this.update(new EvlComment(), wrapper);
    }

    @Override
    public void removeComment(String userId, String commentId) {
        QueryWrapper<EvlComment> wrapper = new QueryWrapper<>();
        wrapper.select(EvlComment.COURSE_ID,EvlComment.USER_ID)
                .eq(EvlComment.ID, commentId);
        EvlComment evlComment = this.getOne(wrapper);
        if(evlComment!=null){
            if(!userId.equals(evlComment.getUserId())){
                throw new MyException(ResultCode.FORBIDDEN,"非发布者或管理员无法删除回复");
            }
            if(baseMapper.deleteById(commentId)==1){
                evlCommentAttitudeService.removeAttitude(commentId);
                evlAgvScoreService.updateAverageScore(evlComment.getCourseId());
            }
        }

    }
}
