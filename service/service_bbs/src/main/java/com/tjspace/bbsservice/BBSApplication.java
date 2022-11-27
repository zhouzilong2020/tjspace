package com.tjspace.bbsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author linfu
 * EnableDiscoveryClient 注册到注册中心--nacos
 * EnableFeignClients 发现服务
 * <p>
 * 由于写了公共配置类， 需要手动引入在basepackage中的swagger公共配置
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.tjspace"})
public class BBSApplication {
    public static void main(String[] args) {
        SpringApplication.run(BBSApplication.class, args);
    }
}
