package ir.cactus;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ApiGateWayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateWayServiceApplication.class);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder, RedisRateLimiter redisRateLimiter){
//        return builder.routes()
//                .route(predicateSpec -> predicateSpec.path("/api/v1/inventory/**")
//                        .filters(predicatefilter ->predicatefilter.rewritePath("/Inventory/?(?<remaining>.*)","/${remaining}")
//                                .addResponseHeader("my_header", LocalDateTime.now().toString())
//                                .requestRateLimiter(config ->config.setRateLimiter(redisRateLimiter())
//                                        .setKeyResolver(keyResolver()))
//                                .retry(retryConfig ->
//                                        retryConfig.setRetries(3)
//                                                .setMethods(HttpMethod.GET)
//                                                .setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),2,true)))
//                        .uri("lb://inventory"))
//                .route()
        return builder.routes()
                .route("product_service_route", r -> r.path("api/v1/products/**")
                        .filters(f -> f.addRequestHeader("X-Gateway", "SpringCloud"))
                        .uri("http://localhost:8080"))
                .route("inventory_service_route", r -> r.path("api/v1/inventory/**")
                        .filters(f -> f.addRequestHeader("X-Gateway", "SpringCloud"))
                        .uri("http://localhost:8081"))
                .build();
    }

    @Bean
    KeyResolver keyResolver() {
        return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
                .defaultIfEmpty("user2");
    }

    @Bean
    RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(1,1,1);
    }
}