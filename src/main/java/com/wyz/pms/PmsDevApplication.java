package com.wyz.pms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wyz.pms.core.mapper")
public class PmsDevApplication {

    public static void main(String[] args) {
        SpringApplication.run(PmsDevApplication.class, args);
    }

}
