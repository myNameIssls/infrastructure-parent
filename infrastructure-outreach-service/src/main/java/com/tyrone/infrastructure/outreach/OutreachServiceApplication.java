package com.tyrone.infrastructure.outreach;

import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.spring.EnableFileStorage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@EnableFileStorage
@SpringBootApplication
public class OutreachServiceApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(OutreachServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("外联服务启动成功");
    }
}