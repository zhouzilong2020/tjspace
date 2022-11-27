package com.tjspace.msmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.tjspace"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MSMApplication {
    public static void main(String[] args) {
        SpringApplication.run(MSMApplication.class, args);
    }
}
