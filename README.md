# springcloud-eureka-servercenter
使用eureka来作为云服务中心，让其它工程来注册服务或者调用服务  
在**eureka-server**的启动类里面添加@EnableEurekaServer，用来启用注册中心的功能  
在**consumer-user**的启动类里面添加一下几个注解  
  
Hystrix-dashboard是一款针对Hystrix进行实时监控的工具，通过Hystrix Dashboard我们可以在直观地看到各Hystrix Command的请求响应时间, 请求成功率等数据。但是只使用Hystrix Dashboard的话, 你只能看到单个应用内的服务信息, 这明显不够. 我们需要一个工具能让我们汇总系统内多个服务的数据并显示到Hystrix Dashboard上, 这个工具就是Turbine.    
# 启动类添加启用Hystrix Dashboard和熔断器   
```@EnableCircuitBreaker //添加启用Hystrix Dashboard和熔断器
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
}```
# 测试  
启动工程后访问 http://localhost:8001/hystrix，将会看到如下界面：  
具体的细节可以访问这个链接：[springcloud(五)：熔断监控Hystrix Dashboard和Turbine][http://www.ityouknow.com/springcloud/2017/05/18/hystrix-dashboard-turbine.html]  **在这里先表示感谢**
