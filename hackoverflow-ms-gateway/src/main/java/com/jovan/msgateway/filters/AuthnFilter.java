package com.jovan.msgateway.filters;

import com.jovan.msgateway.exceptions.AuthnTokenException;
import com.jovan.msgateway.proxies.requests.IAMTokenRequest;
import com.jovan.msgateway.services.IAMProxyService;
import com.jovan.msgateway.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class AuthnFilter implements GlobalFilter {

    @Value("#{'${filters.exclusions.authn}'.split(',')}")
    private List<String> exclusions;

    @Autowired
    private IAMProxyService iamProxyService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("executing authn gateway filter for request bound for {}", exchange.getRequest().getPath());
        if(exclusions.contains(exchange.getRequest().getPath().toString())){
            log.info("authn exclusion applied for path: {}", exchange.getRequest().getPath());
            return chain.filter(exchange);
        }

        try{
            String authToken = null;
            try{
                authToken = exchange.getRequest().getHeaders().get("Authtoken").get(0);
            } catch (NullPointerException ex){
                throw new AuthnTokenException("no Authtoken value found");
            }
            catch (Exception e){
                throw new AuthnTokenException(e.getMessage());
            }
            String jwt = iamProxyService.queryToken(authToken).getJwt();
            JWTUtil.verifyJWT(jwt);
            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate().header("jwt", "Bearer " + jwt).build();
            ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
            return chain.filter(mutatedExchange);
        } catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }
}
