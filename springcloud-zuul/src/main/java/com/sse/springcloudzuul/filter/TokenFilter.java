package com.sse.springcloudzuul.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;


/**
 * Zuul大部分功能都是通过过滤器来实现的，这些过滤器类型对应于请求的典型生命周期。
 * PRE： 这种过滤器在请求被路由之前调用。我们可利用这种过滤器实现身份验证、在集群中选择请求的微服务、记录调试信息等。
 * ROUTING：这种过滤器将请求路由到微服务。这种过滤器用于构建发送给微服务的请求，并使用Apache HttpClient或Netfilx Ribbon请求微服务。
 * POST：这种过滤器在路由到微服务以后执行。这种过滤器可用来为响应添加标准的HTTP Header、收集统计信息和指标、将响应从微服务发送给客户端等。
 * ERROR：在其他阶段发生错误时执行该过滤器。 除了默认的过滤器类型，Zuul还允许我们创建自定义的过滤器类型。例如，我们可以定制一种STATIC类型的过滤器，直接在Zuul中生成响应，而不将请求转发到后端的微服务。
 */
public class TokenFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);


    @Override
    public String filterType() {
        return "pre";//可以在请求被路由之前调用
    }

    @Override
    public int filterOrder() {
        return 0;//filter执行顺序，通过数字指定，数据越小，优先级越高
    }

    @Override
    public boolean shouldFilter() {
        return true;//是否执行该过滤器，默认为false不执行
    }

    /**
     * run方法里面写过滤器具体执行的操作
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info("---->>> TokenFilter,{},{}", request.getMethod(), request.getRequestURL().toString());

        String token = request.getParameter("token");//获取请求的参数

        if (StringUtils.isNotBlank(token)) {
            ctx.setSendZuulResponse(true);//对请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        } else {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(400);
            ctx.setResponseBody("token is empty.");
            ctx.set("isSuccess", false);
            return null;
        }
    }

}
