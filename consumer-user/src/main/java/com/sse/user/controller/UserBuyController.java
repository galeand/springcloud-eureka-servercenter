package com.sse.user.controller;


import com.sse.user.feign.HelloRPC;
import com.sse.user.feign.KaizhongRPC;
import com.sse.user.feign.TangpoetryRPC;
import com.sse.user.feign.TangpoetryRPC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserBuyController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TangpoetryRPC tangpoetryRPC;
    @Autowired
    private HelloRPC hello;
    @Autowired
    private KaizhongRPC kaizhongRPC;


    @ResponseBody
    @GetMapping("/buyticket/{name}")
    public String buy(@PathVariable("name") String name) {
        String object = restTemplate.getForObject("http://PROVIDER-TICKET/ticket", String.class);
        return name + "购买了" + object;

    }

    @ResponseBody
    @GetMapping("/tangtest/{content}")
    public String tangPoetry(@PathVariable("content") String content) {
//        String object = restTemplate.getForObject("http:///tangtest" + "/{" + content + "}", String.class);
        String object = tangpoetryRPC.tangPoetryRPCByEureka(content).toString();
        String res = "全唐诗接口返回：" + object;
        return object;
    }

    @ResponseBody
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        String s = hello.helloRPC(name);
        return s;
    }

    @ResponseBody
    @GetMapping("/kz/{name}")
    public String kz(@PathVariable("name") String name){
        Object o = kaizhongRPC.rpcQuerykz(name);
        return o.toString();
    }


}
