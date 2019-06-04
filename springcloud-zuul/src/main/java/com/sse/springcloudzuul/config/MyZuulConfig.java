package com.sse.springcloudzuul.config;

import com.sse.springcloudzuul.filter.TokenFilter;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class MyZuulConfig {

    /**
     * 通过上面这例子我们可以看出，我们可以使用“PRE”类型的Filter做很多的验证工作，
     * 在实际使用中我们可以结合shiro、oauth2.0等技术去做鉴权、验证。
     * @return
     */
//    @Bean
//    public TokenFilter tokenFilter() {
//        //这个函数要求请求的链接中必须带有一个token.比如“http://localhost:8888/provider-ticket/hello?name=%E6%9D%8E%E9%A3%9E&&token=xx”
//        return new TokenFilter();
//    }
}
