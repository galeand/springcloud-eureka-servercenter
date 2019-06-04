package com.sse.user.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "kaizhong", fallback = KaizhongRPCHystrix.class)
public interface KaizhongRPC {
    @GetMapping("/kz")
    public Object rpcQuerykz(@RequestParam("name") String name);
}
