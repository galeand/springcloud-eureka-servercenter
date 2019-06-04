package com.sse.user.feign;

import com.netflix.ribbon.proxy.annotation.Content;
import org.springframework.stereotype.Component;

@Component
public class TangpoetryRPCHystrix implements TangpoetryRPC{
    @Override
    public Object tangPoetryRPCByEureka(String content) {
        return "错误，发生熔断！";
    }
}
