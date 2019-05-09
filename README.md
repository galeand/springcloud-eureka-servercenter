# springcloud-eureka-servercenter
使用eureka来作为云服务中心，让其它工程来注册服务或者调用服务 
在**eureka-server**的启动类里面添加@EnableEurekaServer，用来启用注册中心的功能 
在**consumer-user**的启动类里面添加一下几个注解 
```@EnableCircuitBreaker //添加启用Hystrix Dashboard和熔断器
@EnableHystrixDashboard
@EnableDiscoveryClient //启用服务注册和发现
@EnableFeignClients //启用feign进行远程调用```
