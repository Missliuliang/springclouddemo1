package com.gatewayweb.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-15 15:34
 */
@Slf4j
@ComponentScan(basePackages = "com.springcloud.auth.client")
public class AccessGatewayFilter implements GlobalFilter {

    private static final String X_CLIENT_USER_TOKEN="x-client-user-token";
    private static final String X_CLIENT_TOKEN="x-client-token";

    //private IAuthService

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        return null;
    }
}
