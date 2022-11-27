package com.tjspace.evlservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tjspace.evlservice.entity.DO.EvlCommentAttitude;
import com.tjspace.evlservice.mapper.EvlCommentAttitudeMapper;
import com.tjspace.evlservice.service.EvlCommentAttitudeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjspace.evlservice.service.EvlCommentService;
import com.tjspace.servicebase.exception.MyException;
import com.tjspace.utils.commonutils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
@Service
public class EvlCommentAttitudeServiceImpl extends ServiceImpl<EvlCommentAttitudeMapper, EvlCommentAttitude> implements EvlCommentAttitudeService {

    @Autowired
    private EvlCommentService evlCommentService;

    @Override
    public Map<String, Boolean> getAttitudes(String userId, Set<String> commentIdSet) {
        QueryWrapper<EvlCommentAttitude> wrapper = new QueryWrapper<>();
        wrapper.select(EvlCommentAttitude.COMMENT_ID, EvlCommentAttitude.TYPE)
                .in(EvlCommentAttitude.COMMENT_ID, commentIdSet)
                .eq(EvlCommentAttitude.USER_ID, userId);
        List<EvlCommentAttitude> list = this.list(wrapper);
        Map<String, Boolean> map = new HashMap<>();
        for (EvlCommentAttitude evlCommentAttitude : list) {
            map.put(evlCommentAttitude.getCommentId(), evlCommentAttitude.getType());
        }
        return map;
    }

    @Override
    public Boolean addOrUpdateAttitude(String userId, String commentId, Boolean type) {
        if (!evlCommentService.isExist(commentId)) {
            throw new MyException(ResultCode.NOT_FOUND, "评价不存在");
        }
        QueryWrapper<EvlCommentAttitude> wrapper = new QueryWrapper<>();
        wrapper.select(EvlCommentAttitude.ID, EvlCommentAttitude.TYPE)
                .eq(EvlCommentAttitude.USER_ID, userId)
                .eq(EvlCommentAttitude.COMMENT_ID, commentId);

        EvlCommentAttitude evlCommentAttitude = this.getOne(wrapper);
        Boolean oldType = null;
        if (evlCommentAttitude == null) {
            evlCommentAttitude = new EvlCommentAttitude();
            evlCommentAttitude.setCommentId(commentId);
            evlCommentAttitude.setUserId(userId);
            evlCommentAttitude.setType(type);
        } else {
            oldType = evlCommentAttitude.getType();
            if (type.equals(oldType)) {
                evlCommentAttitude.setType(null);
            } else {
                evlCommentAttitude.setType(type);
            }
        }
        int deltaType = 1, deltaNotType = 0;  //type代表的态度的变化量 和 type不代表的态度的变化量
        if (oldType != null) {
            if (type.equals(oldType)) {
                deltaType = -1;
            } else {
                deltaNotType = -1;
            }
        }

        int posCount = type ? deltaType : deltaNotType;
        int negCount = !type ? deltaType : deltaNotType;
        this.saveOrUpdate(evlCommentAttitude);
        evlCommentService.updateAttitudeCount(commentId, posCount, negCount);
        return evlCommentAttitude.getType();
    }

    @Override
    public void removeAttitude(String commentId) {
        QueryWrapper<EvlCommentAttitude> wrapper = new QueryWrapper<>();
        wrapper.eq(EvlCommentAttitude.COMMENT_ID,commentId);
        baseMapper.delete(wrapper);
    }
}
