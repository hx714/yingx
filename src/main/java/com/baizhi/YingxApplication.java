package com.baizhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@tk.mybatis.spring.annotation.MapperScan("com.baizhi.dao")
@SpringBootApplication
@org.mybatis.spring.annotation.MapperScan("com.baizhi.dao")
public class YingxApplication {

    public static void main(String[] args) {
        SpringApplication.run(YingxApplication.class, args);
    }

}
