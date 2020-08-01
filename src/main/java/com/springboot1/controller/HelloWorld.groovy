package com.springboot1.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by QingHuan on 2020/7/31 22:52
 */

@RestController
@RequestMapping("/api")
class HelloWorld {

    @GetMapping("/hello")
    String helloword(){
        return "hello word"
    }

}
