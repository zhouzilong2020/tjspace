package com.tjspace.staservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tjspace.staservice.entity.StatisticsDaily;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-12-12
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {
    Integer registerCount(String date);

}
