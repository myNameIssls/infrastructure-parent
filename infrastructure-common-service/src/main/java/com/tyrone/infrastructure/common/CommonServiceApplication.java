package com.tyrone.infrastructure.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class CommonServiceApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(CommonServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("公共服务启动成功");
    }
}