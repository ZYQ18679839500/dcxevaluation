package com.whu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(value = "com.whu.mapper")
public class FcxevaluationApplication {
    public static void main(String[] args) {
        SpringApplication.run(FcxevaluationApplication.class, args);
    }
}
