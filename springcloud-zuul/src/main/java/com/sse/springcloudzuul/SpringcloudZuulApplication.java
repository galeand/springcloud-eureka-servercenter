package com.sse.springcloudzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


/**
 * zuul 服务网关，首先导入它的springcloud-starter依赖，在微服务架构中，后端服务一般不会直接开放给调用端，
 * 而是通过一个API网关根据请求的url，路由到相应的服务。当添加API网关后，第三方调用端和服务提供方之间就创建
 * 了一面墙，这面墙直接与调用方通信进行权限控制，后将请求均衡分发给后台服务端。
 */
@EnableZuulProxy
@SpringBootApplication
public class SpringcloudZuulApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringcloudZuulApplication.class, args);
    }

}
