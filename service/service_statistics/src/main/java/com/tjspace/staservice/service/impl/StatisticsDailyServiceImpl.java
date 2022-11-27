package com.tjspace.staservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjspace.staservice.client.UCenterClient;
import com.tjspace.staservice.entity.StatisticsDaily;
import com.tjspace.staservice.mapper.StatisticsDailyMapper;
import com.tjspace.staservice.service.StatisticsDailyService;
import com.tjspace.utils.commonutils.UniformResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-12-12
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    @Qualifier("UCenterClient")
    @Autowired
    private UCenterClient ucenterClient;

    @Override
    @Transactional
    public Integer registerCount(String date) {
        // 添加记录之前删除表相同日期的数据
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", date);
        baseMapper.delete(wrapper);

        UniformResult r = ucenterClient.countRegister(date);

        // 远程调用得到某一天注册人数
        UniformResult registerR = ucenterClient.countRegister(date);
        Integer countRegister = (Integer) registerR.getData().get("countRegister");

        // 统计分析表
        StatisticsDaily sta = StatisticsDaily.builder()
                .registerNum(countRegister)
                .dateCalculated(date)
                .postNum(100)
                .reviewNum(100).build();
        baseMapper.insert(sta);

        return (Integer) r.getData().get("registerCount");
    }
}
