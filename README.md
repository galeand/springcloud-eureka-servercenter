# springcloud-eureka-zuul-gateway
使用eureka来作为云服务中心，让其它工程来注册服务或者调用服务  
在**eureka-server**的启动类里面添加@EnableEurekaServer，用来启用注册中心的功能  
在**consumer-user**的启动类里面添加一下几个注解  
  
Hystrix-dashboard是一款针对Hystrix进行实时监控的工具，通过Hystrix Dashboard我们可以在直观地看到各Hystrix Command的请求响应时间, 请求成功率等数据。但是只使用Hystrix Dashboard的话, 你只能看到单个应用内的服务信息, 这明显不够. 我们需要一个工具能让我们汇总系统内多个服务的数据并显示到Hystrix Dashboard上, 这个工具就是Turbine.    
# 启动类添加启用Hystrix Dashboard和熔断器   
~~~@EnableCircuitBreaker //添加启用Hystrix Dashboard和熔断器
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
~~~
# gateway的一个问题？
之前用zuul来实现服务转发的时候很简单，一直也没有出现过问题，zuul的转发规则和gateway很相似，都是：**spring cloud zuul已经帮我们做了默认配置。默认情况下，Zuul会代理所有注册到Eureka Server的微服务，并且Zuul的路由规则如下：http://ZUUL_HOST:ZUUL_PORT/微服务在Eureka上的serviceId/**会被转发到serviceId对应的微服务。**  
但是今天在使用gateway转发的时候一直出现404转发错误，我调试了很久，然后以为是什么类似于** @EnableZuulProxy** 的注解没有加上，但是后来我才发现，tm的gateway不支持大小写转换，我tm注册到eureka服务中心的服务名称是大写的，比如“PROVIDER-TICKET”，那么请求路径也必须大写，改成大写之后瞬间转发成功。。。

# 测试  
启动工程后访问 http://localhost:8001/hystrix，将会看到如下界面：  
具体的细节可以访问这个链接：[springcloud(五)：熔断监控Hystrix Dashboard和Turbine](http://www.ityouknow.com/springcloud/2017/05/18/hystrix-dashboard-turbine.html)

  
# springcloud入门介绍
SpringCloud一些介绍和入门，一下简介来源于博客[中小型互联网公司微服务实践-经验和教训](http://www.ityouknow.com/springcloud/2017/10/19/micro-service-practice.html)在此表示感谢。

# 什么是Spring Boot  
Spring Boot是由Pivotal团队提供的全新框架，其设计目的是用来简化新Spring应用的初始搭建以及开发过程。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。用我的话来理解，就是Spring Boot其实不是什么新的框架，它默认配置了很多框架的使用方式，就像maven整合了所有的jar包，Spring Boot整合了所有的框架（不知道这样比喻是否合适）。
Spring Boot简化了基于Spring的应用开发，通过少量的代码就能创建一个独立的、产品级别的Spring应用。 Spring Boot为Spring平台及第三方库提供开箱即用的设置，这样你就可以有条不紊地开始。Spring Boot的核心思想就是约定大于配置，多数Spring Boot应用只需要很少的Spring配置。采用Spring Boot可以大大的简化你的开发模式，所有你想集成的常用框架，它都有对应的组件支持。  

# Spring Cloud都做了哪些事  
Spring Cloud是一系列框架的有序集合。它利用Spring Boot的开发便利性巧妙地简化了分布式系统基础设施的开发，如服务发现注册、配置中心、消息总线、负载均衡、断路器、数据监控等，都可以用Spring Boot的开发风格做到一键启动和部署。Spring并没有重复制造轮子，它只是将目前各家公司开发的比较成熟、经得起实际考验的服务框架组合起来，通过Spring Boot风格进行再封装屏蔽掉了复杂的配置和实现原理，最终给开发者留出了一套简单易懂、易部署和易维护的分布式系统开发工具包  
**以下为Spring Cloud的核心功能：**  
分布式/版本化配置  
服务注册和发现  
路由  
服务和服务之间的调用  
负载均衡  
断路器  
分布式消息传递    
**通过这张图，我们来了解一下各组件配置使用运行流程：**  
1、请求统一通过API网关（Zuul）来访问内部服务.  
2、网关接收到请求后，从注册中心（Eureka）获取可用服务   
3、由Ribbon进行均衡负载后，分发到后端具体实例  
4、微服务之间通过Feign进行通信处理业务  
5、Hystrix负责处理服务超时熔断  
6、Turbine监控服务间的调用和熔断相关指标  

# Spring Cloud体系介绍  
上图只是Spring Cloud体系的一部分，Spring Cloud共集成了19个子项目，里面都包含一个或者多个第三方的组件或者框架！  
Spring Cloud 工具框架  
1、Spring Cloud Config 配置中心，利用git集中管理程序的配置。   
2、Spring Cloud Netflix 集成众多Netflix的开源软件  
3、Spring Cloud Bus 消息总线，利用分布式消息将服务和服务实例连接在一起，用于在一个集群中传播状态的变化   
4、Spring Cloud for Cloud Foundry 利用Pivotal Cloudfoundry集成你的应用程序  
5、Spring Cloud Cloud Foundry Service Broker 为建立管理云托管服务的服务代理提供了一个起点。  
6、Spring Cloud Cluster 基于Zookeeper, Redis, Hazelcast, Consul实现的领导选举和平民状态模式的抽象和实现。  
7、Spring Cloud Consul 基于Hashicorp Consul实现的服务发现和配置管理。  
8、Spring Cloud Security 在Zuul代理中为OAuth2 rest客户端和认证头转发提供负载均衡  
9、Spring Cloud Sleuth SpringCloud应用的分布式追踪系统，和Zipkin，HTrace，ELK兼容。  
10、Spring Cloud Data Flow 一个云本地程序和操作模型，组成数据微服务在一个结构化的平台上。  
11、Spring Cloud Stream 基于Redis,Rabbit,Kafka实现的消息微服务，简单声明模型用以在Spring Cloud应用中收发消息。  
12、Spring Cloud Stream App Starters 基于Spring Boot为外部系统提供spring的集成  
13、Spring Cloud Task 短生命周期的微服务，为SpringBooot应用简单声明添加功能和非功能特性。  
14、Spring Cloud Task App Starters  
15、Spring Cloud Zookeeper 服务发现和配置管理基于Apache Zookeeper。  
16、Spring Cloud for Amazon Web Services 快速和亚马逊网络服务集成。  
17、Spring Cloud Connectors 便于PaaS应用在各种平台上连接到后端像数据库和消息经纪服务。  
18、Spring Cloud Starters （项目已经终止并且在Angel.SR2后的版本和其他项目合并）  
19、Spring Cloud CLI 插件用Groovy快速的创建Spring Cloud组件应用。  

