package com.jovan.msgateway.proxies;

import com.jovan.msgateway.proxies.responses.IAMTokenResponse;
import feign.HeaderMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "iam-proxy", url = "${ms.iam.uri}")
public interface IAMProxy {

    @PostMapping("/api/v1/jwt")
    IAMTokenResponse token(@RequestHeader Map headers);

}
