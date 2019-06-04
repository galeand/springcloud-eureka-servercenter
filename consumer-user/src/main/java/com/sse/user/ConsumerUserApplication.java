package com.sse.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableCircuitBreaker //添加启用Hystrix Dashboard和熔断器
@EnableHystrixDashboard
@EnableDiscoveryClient //启用服务注册和发现
@EnableFeignClients //启用feign进行远程调用
@SpringBootApplication
public class ConsumerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerUserApplication.class, args);
    }


    @LoadBalanced //启用负载均衡机制
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();//这个模板用来发rest风格的url请求
    }
}
