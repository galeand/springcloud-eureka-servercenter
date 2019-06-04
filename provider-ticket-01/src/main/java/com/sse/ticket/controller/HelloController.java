package com.sse.ticket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //注册到eureka上面提供远程调用
    @GetMapping("/hello")
    public String helloRPC(@RequestParam String name) {
        return "Hello " + name + "," + "this is first message!";
    }
}
