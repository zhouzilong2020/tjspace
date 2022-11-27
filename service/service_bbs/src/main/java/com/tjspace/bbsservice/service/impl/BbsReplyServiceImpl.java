package com.tjspace.bbsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjspace.bbsservice.client.InfoClient;
import com.tjspace.bbsservice.entity.DO.BbsPost;
import com.tjspace.bbsservice.entity.DO.BbsReply;
import com.tjspace.bbsservice.entity.DO.BbsSubreply;
import com.tjspace.bbsservice.entity.VO.ReplyVO;
import com.tjspace.bbsservice.entity.BO.SubreplyListBO;
import com.tjspace.bbsservice.mapper.BbsReplyMapper;
import com.tjspace.bbsservice.service.BbsPostService;
import com.tjspace.bbsservice.service.BbsReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjspace.bbsservice.service.BbsSubreplyService;
import com.tjspace.servicebase.entity.DTO.UserPublicInfoDTO;
import com.tjspace.servicebase.exception.MyException;
import com.tjspace.utils.commonutils.ResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class BbsReplyServiceImpl extends ServiceImpl<BbsReplyMapper, BbsReply> implements BbsReplyService {

    @Autowired
    private BbsPostService bbsPostService;

    @Autowired
    private BbsSubreplyService bbsSubreplyService;

    @Autowired
    private InfoClient infoClient;

    @Override
    public BbsReply addReply(String userId, String postId, String content) {
        if (!bbsPostService.isExist(postId)) {
            throw new MyException(ResultCode.NOT_FOUND, "帖子不存在");
        }
        BbsReply bbsReply = new BbsReply();
        bbsReply.setUserId(userId);
        bbsReply.setPostId(postId);
        bbsReply.setContent(content);

        this.save(bbsReply);
        bbsPostService.increaseReplyCount(postId);
        return bbsReply;
    }

    @Override
    public String getPostId(String replyId) {
        QueryWrapper<BbsReply> wrapper = new QueryWrapper<>();
        wrapper.select(BbsReply.POST_ID).eq(BbsReply.ID, replyId);
        BbsReply bbsReply = this.getOne(wrapper);
        if (bbsReply != null) {
            return bbsReply.getPostId();
        } else {
            throw new MyException(ResultCode.NOT_FOUND, "回复不存在");
        }
    }

    @Override
    public Map<String, Object> getReplies(Integer currentPage, Integer limit, String postId, Boolean onlyPoster) {
        BbsPost bbsPost = bbsPostService.getPost(postId,
                BbsPost.USER_ID,
                BbsPost.CONTENT,
                BbsPost.GMT_CREATE);

        Map<String, Object> returnMap = new HashMap<>(7);

        int size = limit;

        //若当前为第一页，帖子的内容作为返回列表的第一条
        if (currentPage == 1) {
            size--;  //剩余需要查询的回复数量减1
        }
        Map<String, UserPublicInfoDTO> userPublicInfoMap = null;

        Page<ReplyVO> bbsReplyPage = new Page<>(currentPage, size);
        if (size > 0) {
            baseMapper.selectReplyPage(bbsReplyPage, postId, "");

            if (!bbsReplyPage.getRecords().isEmpty()) {
                Set<String> replyIdSet = new HashSet<>();
                Set<String> userIdSet = new HashSet<>();

                for (ReplyVO reply : bbsReplyPage.getRecords()) {
                    replyIdSet.add(reply.getId());
                    userIdSet.add(reply.getUserId());
                }
                Map<String, SubreplyListBO> bbsSubreplyMap = bbsSubreplyService.getSubreplies(replyIdSet);

                for (ReplyVO reply : bbsReplyPage.getRecords()) {
                    if (bbsSubreplyMap.containsKey(reply.getId())) {
                        List<BbsSubreply> subreplyList = bbsSubreplyMap.get(reply.getId()).getSubreplyList();
                        for (BbsSubreply bbsSubreply : subreplyList) {
                            userIdSet.add(bbsSubreply.getUserId());
                        }
                        reply.setSubreplyList(subreplyList);
                    } else {
                        reply.setSubreplyList(new ArrayList<>());
                    }
                }
                userPublicInfoMap = infoClient.getUsersPublicInfo(userIdSet, UserPublicInfoDTO.NICKNAME, UserPublicInfoDTO.AVATAR);
            }
        }
        if (currentPage == 1) {
            ReplyVO replyVO = new ReplyVO();
            BeanUtils.copyProperties(bbsPost, replyVO);
            replyVO.setId("");
            bbsReplyPage.getRecords().add(0, replyVO);
        }
        returnMap.put("replyList", bbsReplyPage.getRecords());
        if(userPublicInfoMap==null){
            returnMap.put("usersInfo",new HashMap<>());
        }else {
            returnMap.put("usersInfo",userPublicInfoMap);
        }
        returnMap.put("currentPage", Math.max(1, bbsReplyPage.getCurrent()));
        returnMap.put("totalPage", Math.max(1, Math.ceil(1.0 * (bbsReplyPage.getTotal() + 1) / limit)));
        return returnMap;
    }

    @Override
    public void removeReply(String userId, String replyId) {
        QueryWrapper<BbsReply> wrapper = new QueryWrapper<>();
        wrapper.select(BbsReply.POST_ID, BbsReply.USER_ID)
                .eq(BbsReply.ID, replyId);
        BbsReply bbsReply = this.getOne(wrapper);
        if (bbsReply != null) {
            if (!userId.equals(bbsReply.getUserId())) {
                throw new MyException(ResultCode.FORBIDDEN, "非发布者或管理员无法删除回复");
            }
            if(baseMapper.deleteById(replyId)==1){
                int deleteCount = bbsSubreplyService.removeSubreplies(replyId);
                bbsPostService.decreaseReplyCount(bbsReply.getPostId(), deleteCount + 1);
            }
        }
    }

    @Override
    public void removeReplies(String postId) {
        QueryWrapper<BbsReply> wrapper = new QueryWrapper<>();
        wrapper.select(BbsReply.ID).eq(BbsReply.POST_ID, postId);
        List<BbsReply> bbsReplyList = this.list(wrapper);
        if (bbsReplyList != null) {
            List<String> replyIdList = new ArrayList<>();
            for (BbsReply bbsReply : bbsReplyList) {
                replyIdList.add(bbsReply.getId());
            }
            int deleteCount = baseMapper.delete(wrapper) + bbsSubreplyService.removeSubreplies(replyIdList);
            bbsPostService.decreaseReplyCount(postId, deleteCount);
        }
    }

}
