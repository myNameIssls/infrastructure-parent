package com.tyrone.infrastructure.sample.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@Configuration
public class Config {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

        ZonedDateTime before = LocalDateTime.now().minusDays(1).atZone(ZoneId.systemDefault());
        ZonedDateTime after = LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault());
        return builder.routes()
                .route("jd_router_1", p -> p.path("/fresh").uri("https://fresh.jd.com/"))
                .route("ciyun-router", p -> p.path("/iciyun").uri("https://iciyun.com/#/index"))
                .route("yq", p -> p.path("/yq").uri("https://yuque.com/"))
                .route("taobao", p -> p.path("/tb").uri("http://taobao.com/"))

                .route("filter_route_0", p -> p.path("/sample/gateway/**").filters(f->f.addRequestParameter("name", "Tyrone")).uri("http://localhost:7070"))

                .route("cookie_route", p -> p.cookie("chocolate", "ch.p").uri("http://localhost:7070/sample/gateway/cookie"))
//                .route("between", p -> p.between(before, after).uri("http://jd.com"))
//                .route("after", p -> p.after(before).uri("http://baidu.com"))
//                .route("before", p -> p.before(after).uri("http://baidu.com"))
                .build();
    }





}
