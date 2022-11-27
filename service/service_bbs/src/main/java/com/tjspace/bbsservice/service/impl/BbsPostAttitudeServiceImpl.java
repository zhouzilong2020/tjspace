package com.tjspace.bbsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tjspace.bbsservice.entity.DO.BbsPostAttitude;
import com.tjspace.bbsservice.mapper.BbsPostAttitudeMapper;
import com.tjspace.bbsservice.service.BbsPostAttitudeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjspace.bbsservice.service.BbsPostService;
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
 * 服务实现类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
@Service
public class BbsPostAttitudeServiceImpl extends ServiceImpl<BbsPostAttitudeMapper, BbsPostAttitude> implements BbsPostAttitudeService {

    @Autowired
    private BbsPostService bbsPostService;

    @Override
    public Boolean addOrUpdateAttitude(String userId, String postId, Boolean type) {
        if (!bbsPostService.isExist(postId)) {
            throw new MyException(ResultCode.NOT_FOUND, "帖子不存在");
        }
        QueryWrapper<BbsPostAttitude> wrapper = new QueryWrapper<>();
        wrapper.select(BbsPostAttitude.ID, BbsPostAttitude.TYPE)
                .eq(BbsPostAttitude.USER_ID, userId)
                .eq(BbsPostAttitude.POST_ID, postId);

        BbsPostAttitude bbsPostAttitude = this.getOne(wrapper);
        Boolean oldType = null;
        if (bbsPostAttitude == null) {
            bbsPostAttitude = new BbsPostAttitude();
            bbsPostAttitude.setPostId(postId);
            bbsPostAttitude.setUserId(userId);
            bbsPostAttitude.setType(type);
        } else {
            oldType = bbsPostAttitude.getType();
            if (type.equals(oldType)) {
                bbsPostAttitude.setType(null);
            } else {
                bbsPostAttitude.setType(type);
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
        this.saveOrUpdate(bbsPostAttitude);
        bbsPostService.updateAttitudeCount(postId, posCount, negCount);
        return bbsPostAttitude.getType();
    }

    @Override
    public Boolean getAttitude(String userId, String postId) {
        QueryWrapper<BbsPostAttitude> wrapper = new QueryWrapper<>();
        wrapper.select(BbsPostAttitude.TYPE)
                .eq(BbsPostAttitude.POST_ID, postId)
                .eq(BbsPostAttitude.USER_ID, userId);
        BbsPostAttitude bbsPostAttitude = this.getOne(wrapper);
        return bbsPostAttitude != null ? bbsPostAttitude.getType() : null;
    }

    @Override
    public Map<String, Boolean> getAttitudes(String userId, Set<String> postIdSet) {
        QueryWrapper<BbsPostAttitude> wrapper = new QueryWrapper<>();
        wrapper.select(BbsPostAttitude.POST_ID, BbsPostAttitude.TYPE)
                .in(BbsPostAttitude.POST_ID, postIdSet)
                .eq(BbsPostAttitude.USER_ID, userId);
        List<BbsPostAttitude> list = this.list(wrapper);
        Map<String, Boolean> map = new HashMap<>();
        for (BbsPostAttitude bbsPostAttitude : list) {
            map.put(bbsPostAttitude.getPostId(), bbsPostAttitude.getType());
        }
        return map;
    }

    @Override
    public void removeAttitude(String postId) {
        QueryWrapper<BbsPostAttitude> wrapper = new QueryWrapper<>();
        wrapper.eq(BbsPostAttitude.POST_ID, postId);
        this.remove(wrapper);
    }
}
