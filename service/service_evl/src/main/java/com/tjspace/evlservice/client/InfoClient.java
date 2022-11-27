package com.tjspace.evlservice.client;

import com.tjspace.servicebase.entity.DTO.UserPublicInfoDTO;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Set;

/**
 * 调用端接口， 调用Info服务
 * Info 客户端服务
 * 定义调用方法的路径，实现类在服务端的方法对应
 * 不需要实现类， 远程会调用对端服务的东西 PRC！
 * TODO:把client注入到相应的服务中
 *
 * @author Fu Lin
 */
@FeignClient(name = "service-info")
@Component
public interface InfoClient {
    @GetMapping("inner/courses/{courseId}/existence")
    Boolean isExist(
            @ApiParam(value = "课程ID")
            @PathVariable String courseId
    );

    @GetMapping("inner/public-infos")
    Map<String, UserPublicInfoDTO> getUsersPublicInfo(
            @ApiParam(value = "若干用户ID",required = true)
            @RequestParam Set<String> userIds,
            @ApiParam(value = "筛选字段")
            @RequestParam String ... attributes
    );
}
