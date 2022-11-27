package com.tjspace.bbsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tjspace.bbsservice.entity.DO.BbsSubreply;
import com.tjspace.bbsservice.entity.BO.SubreplyListBO;
import com.tjspace.bbsservice.mapper.BbsSubreplyMapper;
import com.tjspace.bbsservice.service.BbsPostService;
import com.tjspace.bbsservice.service.BbsReplyService;
import com.tjspace.bbsservice.service.BbsSubreplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjspace.servicebase.exception.MyException;
import com.tjspace.utils.commonutils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
@Service
public class BbsSubreplyServiceImpl extends ServiceImpl<BbsSubreplyMapper, BbsSubreply> implements BbsSubreplyService {

    @Autowired
    private BbsReplyService bbsReplyService;

    @Autowired
    private BbsPostService bbsPostService;

    @Override
    public BbsSubreply addSubreply(String userId, String replyId, String content) {
        String postId = bbsReplyService.getPostId(replyId);

        BbsSubreply bbsSubreply = new BbsSubreply();
        bbsSubreply.setUserId(userId);
        bbsSubreply.setReplyId(replyId);
        bbsSubreply.setContent(content);
        this.save(bbsSubreply);
        bbsPostService.increaseReplyCount(postId);
        return bbsSubreply;
    }

    @Override
    public Map<String, SubreplyListBO> getSubreplies(Set<String> replyIdSet) {
        return baseMapper.selectSubreplyListMap(replyIdSet);
    }

    @Override
    public void removeSubreply(String userId, String subreplyId) {
        QueryWrapper<BbsSubreply> wrapper = new QueryWrapper<>();
        wrapper.select(BbsSubreply.REPLY_ID, BbsSubreply.USER_ID)
                .eq(BbsSubreply.ID, subreplyId);
        BbsSubreply bbsSubreply = this.getOne(wrapper);
        if (bbsSubreply != null) {
            if (!userId.equals(bbsSubreply.getUserId())) {
                throw new MyException(ResultCode.FORBIDDEN, "非发布者或管理员无法删除子回复");
            }
            String replyId = bbsSubreply.getReplyId();
            String postId = null;
            try {
                postId = bbsReplyService.getPostId(replyId);
            } catch (Exception e) {
                //empty
            }
            if (baseMapper.deleteById(subreplyId) == 1) {
                if (postId != null) {
                    bbsPostService.decreaseReplyCount(postId, 1);
                }
            }
        }
    }

    @Override
    public int removeSubreplies(String replyId) {
        QueryWrapper<BbsSubreply> wrapper = new QueryWrapper<>();
        wrapper.eq(BbsSubreply.REPLY_ID, replyId);
        return baseMapper.delete(wrapper);
    }

    @Override
    public int removeSubreplies(List<String> replyIdList) {
        QueryWrapper<BbsSubreply> wrapper = new QueryWrapper<>();
        wrapper.in(BbsSubreply.REPLY_ID, replyIdList);
        return baseMapper.delete(wrapper);
    }

}
