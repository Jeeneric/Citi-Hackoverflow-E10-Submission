package com.jovan.msgateway.services;

import com.jovan.msgateway.proxies.IAMProxy;
import com.jovan.msgateway.proxies.requests.IAMTokenRequest;
import com.jovan.msgateway.proxies.responses.IAMTokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
@Slf4j
public class IAMProxyService {

    @Autowired
    private IAMProxy iamProxy;

    public IAMTokenResponse queryToken(String authToken){
        log.info("processing authToken {}", authToken);
        Map<String, Object> headers = new HashMap<>();
        headers.put("Authtoken", authToken.toString());
        log.info("headers : {}", headers.toString());
        return iamProxy.token(headers);
    }


}
