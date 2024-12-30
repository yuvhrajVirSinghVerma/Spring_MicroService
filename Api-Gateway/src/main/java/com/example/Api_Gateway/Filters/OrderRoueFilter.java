package com.example.Api_Gateway.Filters;


import com.example.Api_Gateway.Service.JwtService;
import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderRoueFilter extends AbstractGatewayFilterFactory<OrderRoueFilter.Config> {

    private final JwtService jwtService;
    public OrderRoueFilter(JwtService jwtService){
        super(Config.class);
        this.jwtService=jwtService;

    }
    @Override
    public GatewayFilter apply(Config config) {
       return (exchange,chain)->{
           System.out.println("config++++ "+config.toString() +" : "+config.isfeatureEnabled);
           System.out.println("order route specific filter");
           String Authheader=exchange.getRequest().getHeaders().getFirst("Authorization");
//           if(Authheader==null){
//               exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//               return exchange.getResponse().setComplete();
//           }
//
//           String token=Authheader.split("Bearer")[1];
//
//           Long id=jwtService.getUserId(token);
//
//           //we can create an auth service for validation token using spring security and then pass the valid token to other microservices
//           exchange
//                   .getRequest()
//                   .mutate()
//                   .header("X-User-Id",id.toString())
//                   .build();
           return chain.filter(exchange);
        };
    }


    @Data
    public static class Config{
        private boolean isfeatureEnabled;
    }
}
