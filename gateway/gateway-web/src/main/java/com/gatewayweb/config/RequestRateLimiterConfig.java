package com.gatewayweb.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-15 09:53
 */
@Component
public class RequestRateLimiterConfig {

    @Bean
    public KeyResolver remoteAddressKeyResolver(){
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

    @Bean
    public KeyResolver apiKeyResolver(){
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }

    @Bean
    public KeyResolver userKeyResolver(){
        return  exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("username"));
    }
}
