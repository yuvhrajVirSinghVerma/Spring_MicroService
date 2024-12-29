package com.example.Api_Gateway.Filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GlobalLoggingFilters implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("pre filter logic"+exchange.getRequest().toString());
        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            System.out.println("post filter logic"+exchange.getResponse().toString());
        }));
    }

    @Override
    public int getOrder() {
        return 5;//order of this filter in which it get executed
    }
}
