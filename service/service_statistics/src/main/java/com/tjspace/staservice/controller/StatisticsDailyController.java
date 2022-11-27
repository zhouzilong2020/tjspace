package com.tjspace.staservice.controller;


import com.tjspace.staservice.service.StatisticsDailyService;
import com.tjspace.utils.commonutils.UniformResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-12-12
 */
@RestController
@RequestMapping("/staservice/statistics-daily")
@CrossOrigin
public class StatisticsDailyController {
    private StatisticsDailyService staService;

    @Autowired
    StatisticsDailyController(StatisticsDailyService staService) {
        this.staService = staService;
    }

    @PostMapping("registerCount/{date}")
    public UniformResult registerCount(
            @PathVariable String date
    ) {
        Integer count = staService.registerCount(date);

        return UniformResult.ok();
    }

}

