package com.bgur;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @projectName: bgur
 * @package: com.bgur
 * @className: BgurApplication
 * @author: huihui
 * @description: TODO
 * @date: 2025/6/25 14:09
 * @version: 1.0
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.bgur.mapper")
public class BgurApplication {

    public static void main(String[] args) {
        SpringApplication.run(BgurApplication.class,args);
    }
}
