package com.vanminh.MyPortfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableAsync
public class MyPortfolioApplication {

    // Biến tĩnh để lưu thời gian khởi động
    public static final long START_TIME = System.currentTimeMillis();

    public static void main(String[] args) {
        SpringApplication.run(MyPortfolioApplication.class, args);

    }

}
