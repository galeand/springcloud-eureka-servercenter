package com.sse.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "provider-ticket", fallback = HelloRPCHystrix.class)
public interface HelloRPC {
    @GetMapping("/hello")
    public String helloRPC(@RequestParam("name") String name);
}
