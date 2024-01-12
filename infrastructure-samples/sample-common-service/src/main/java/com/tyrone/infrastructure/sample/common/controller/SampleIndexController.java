package com.tyrone.infrastructure.sample.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/sample")
public class SampleIndexController {

    @GetMapping("/index")
    public Object index(){
        return "sample index response";
    }

}
