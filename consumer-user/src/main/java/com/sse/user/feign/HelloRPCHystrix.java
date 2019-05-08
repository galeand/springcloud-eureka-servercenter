package com.sse.user.feign;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component //将其添加到spring容器中
public class HelloRPCHystrix implements HelloRPC {
    @Override
    public String helloRPC(@RequestParam("name") String name) {
        return "Hello " + name + ",发生熔断，信息发送失败！";
    }
}
