package com.sse.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "tangpoetry")
public interface TangpoetryRPC {
    @GetMapping("/tangtest")
    public Object tangPoetryRPCByEureka(@RequestParam("content") String content);
}
