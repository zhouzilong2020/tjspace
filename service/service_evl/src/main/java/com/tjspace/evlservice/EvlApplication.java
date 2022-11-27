package com.tjspace.evlservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhouzilong
 * // 编写启动类
 * TODO: 创建mapper扫描等配置类
 * 由于写了公共配置类， 需要手动引入在basepackage中的swagger公共配置
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.tjspace"})
@EnableDiscoveryClient
@EnableFeignClients
public class EvlApplication {
    public static void main(String[] args) {
        SpringApplication.run(EvlApplication.class, args);
    }
}
