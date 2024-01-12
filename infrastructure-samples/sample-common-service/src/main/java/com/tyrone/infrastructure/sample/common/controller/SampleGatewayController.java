package com.tyrone.infrastructure.sample.common.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sample/gateway")
public class SampleGatewayController {

    @GetMapping("/after")
    public Object after(){
        return "测试After路由断言工厂";
    }


    //  http://localhost:7070/sample/gateway/cookie
    @GetMapping("/cookie")
    public String cookie(){
        log.info("cookie路由断言工厂测试");
        System.out.printf("cookie路由断言工厂测试");
        return "cookie路由断言工厂测试";
    }


    @RequestMapping("/addRequestParameter")
    public Object addRequestParameter(HttpServletRequest request){

        return request.getParameterMap();
    }

    @GetMapping("/header")
    public Object header(@RequestHeader(value = "X-Request-Id") String requestId){

        return "X-Request-Id:" + requestId;
    }

    @GetMapping("/host")
    public Object host(HttpServletRequest request){

        String host = request.getHeader("Host");
        String remoteHost = request.getRemoteHost();
        Map<String, String> map = new HashMap<>();
        map.put("host", host);
        map.put("remoteHost", remoteHost);
        return map;
    }

}
