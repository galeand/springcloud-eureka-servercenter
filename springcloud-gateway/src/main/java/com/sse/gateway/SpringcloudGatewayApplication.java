package com.sse.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * Spring Cloud Gateway 是 Spring Cloud 的一个全新项目，该项目是基于 Spring 5.0，Spring Boot 2.0 和 Project Reactor 等技术开发的网关，它旨在为微服务架构提供一种简单有效的统一的 API 路由管理方式。
 * Spring Cloud Gateway 作为 Spring Cloud 生态系统中的网关，目标是替代 Netflix Zuul，其不仅提供统一的路由方式，并且基于 Filter 链的方式提供了网关基本的功能，例如：安全，监控/指标，和限流。
 * 相关概念:
 * Route（路由）：这是网关的基本构建块。它由一个 ID，一个目标 URI，一组断言和一组过滤器定义。如果断言为真，则路由匹配。
 * Predicate（断言）：这是一个 Java 8 的 Predicate。输入类型是一个 ServerWebExchange。我们可以使用它来匹配来自 HTTP 请求的任何内容，例如 headers 或参数。
 * Filter（过滤器）：这是org.springframework.cloud.gateway.filter.GatewayFilter的实例，我们可以使用它修改请求和响应。
 */
//@EnableDiscoveryClient
//@EnableEurekaClient
@SpringBootApplication
public class SpringcloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        //注意下面的r是一个lambda表达式传入一段业务逻辑，其中r是一个PredicateSpec类型的对象
        //
        routes.route("path-route",
                r -> r.path("/provider-ticket").uri("localhost:9000/"));
        routes.route("path2-route",
                r -> r.path(new String[]{"/about", "/about2"}).uri("http://ityouknow.com"));
        return routes.build();

//        return builder.routes().route("path-route",
//                r -> r.path(new String[]{"/baidu", "/about", "/aaa"}).uri("https://www.runoob.com/java/java8-new-features.html")).build();

        //如果我想在添加一条路由规则，怎么办呢?
        //builder.routes().route("path2-route",
        //                r -> r.path("/about").uri("https://www.baidu.com/")).build();

    }

}
