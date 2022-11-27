package com.tjspace.staservice.client;

import com.tjspace.utils.commonutils.UniformResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhouzilong
 */
@Component
@FeignClient("com.tjspace.msmservice.service-ucenter")
public interface UCenterClient {

    @GetMapping("/ucenterservice/countRegister/{date}")
    public UniformResult countRegister(@PathVariable("date") String date);
}
