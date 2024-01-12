package com.tyrone.infrastructure.sample.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Slf4j
@SpringBootApplication
public class SampleGatewayApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SampleGatewayApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Spring Cloud Gateway Sample start successful");
    }


    //@Bean注解就是将CorsWebFilter对象交给IOC容器管理
    @Bean
    public CorsWebFilter corsWebFilter(){
        //org.springframework.web.cors.reactive.CorsConfigurationSource的实现类
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //跨域配置类
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 配置跨域
        //允许哪些头跨域
        corsConfiguration.addAllowedHeader("*");
        //允许哪些请求跨域
        corsConfiguration.addAllowedMethod("*");
        //允许哪些请求来源跨域 如果不实用*，可以配置多个添加请求来源
        corsConfiguration.addAllowedOrigin("*");
        //是否允许携带cookie进行跨域
        corsConfiguration.setAllowCredentials(true);
        //对接口进行配置，“/*”代表所有，“/**”代表适配的所有接口
        source.registerCorsConfiguration("/**", corsConfiguration);
        //CorsWebFilter的构造器需要传递一个
        //org.springframework.web.cors.reactive.CorsConfigurationSource的接口作为参数
        //接口不能实例化，所以选择CorsConfigurationSource的实现类
        //UrlBasedCorsConfigurationSource作为参数
        return new CorsWebFilter(source);
    }

//    @RequestMapping("/sample/addRequestParameter")
//    public Object addRequestParameter(HttpServletRequest request){
//
//        return request.getParameterMap();
//    }

}