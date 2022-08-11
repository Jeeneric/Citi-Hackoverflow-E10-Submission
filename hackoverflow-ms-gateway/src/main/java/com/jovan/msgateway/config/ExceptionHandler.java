package com.jovan.msgateway.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jovan.msgateway.exceptions.AuthnTokenException;
import com.jovan.msgateway.exceptions.ExceptionResponse;
import com.jovan.msgateway.exceptions.InvalidJWTException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Component
@Order(-2)
public class ExceptionHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        log.error("exception ocurred: {}", ex.getMessage());
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        if(Optional.ofNullable(ex).isPresent()){
            if(ex instanceof FeignException){
                log.info("FeignException caught");
                FeignException exception = (FeignException) ex;
                response.setStatusCode(HttpStatus.valueOf(exception.status()));
            } else if (ex instanceof InvalidJWTException) {
                log.info("InvalidJWTException caught");
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
            } else if(ex instanceof AuthnTokenException){
                log.info("InvalidJWTException caught");
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
            }
            else {
                log.info("Unexpected exception caught: {}", ex.getStackTrace());
                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                return Mono.error(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "unexpected error occurred"));
            }
        }

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try{
            DataBuffer buffer = response
                    .bufferFactory()
                    .wrap(
                            objectWriter.writeValueAsBytes(ExceptionResponse.builder().errorDetail(ex.getMessage()).build())
                    );
            return response.writeWith(Mono.just(buffer));
        } catch (JsonProcessingException e) {
            return Mono.empty();
        }
    }
}
