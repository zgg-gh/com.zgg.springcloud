package org.example.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator custmRouterlocator(RouteLocatorBuilder routeLocatorBuilder) {

        RouteLocatorBuilder.Builder builder = routeLocatorBuilder.routes();
        builder.route("route——test",
                r->r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        builder.route("route——tests",
                r->r.path("/guoji").uri("http://news.baidu.com/guoji")).build();
        return builder.build();
    }
}
