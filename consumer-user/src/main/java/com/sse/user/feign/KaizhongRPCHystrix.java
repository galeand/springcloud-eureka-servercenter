package com.sse.user.feign;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KaizhongRPCHystrix implements KaizhongRPC {
    @Override
    public Object rpcQuerykz(String name) {
        return "错误，发生熔断";
    }
}
