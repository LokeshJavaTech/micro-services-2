package com.lokesh.gateway_server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.time.Duration;

@Configuration
public class RouteLocators {

    @Autowired
    private RedisRateLimiter redisRateLimiter;

    @Autowired
    private KeyResolver keyResolver;

    @Bean
    public RouteLocator eazyBankRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/eazybank/accounts/**")
                        .filters(f -> f
                                        .rewritePath("/eazybank/accounts/(?<segment>.*)", "/${segment}")
                                        .circuitBreaker(config -> config
                                                                        .setName("accountsCircuitBreaker")
                                                                        .setFallbackUri("forward:/contactSupport")
                                        )
                        )
                        .uri("lb://ACCOUNTS"))
                .route(p -> p
                        .path("/eazybank/loans/**")
                        .filters(f -> f
                                        .rewritePath("/eazybank/loans/(?<segment>.*)", "/${segment}")
                                        .retry(retryConfig -> retryConfig
                                                                        .setRetries(3)
                                                                        .setMethods(HttpMethod.GET)
                                                                        .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)
                                        )
                        )
                        .uri("lb://LOANS"))
                .route(p -> p
                        .path("/eazybank/cards/**")
                        .filters(f -> f
                                        .rewritePath("/eazybank/cards/(?<segment>.*)", "/${segment}")
                                        .requestRateLimiter(config -> config
                                                                            .setKeyResolver(keyResolver)
                                                                            .setRateLimiter(redisRateLimiter)
                                        )
                        )
                        .uri("lb://CARDS"))
                .build();
    }

}
