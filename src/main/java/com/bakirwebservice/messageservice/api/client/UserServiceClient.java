package com.bakirwebservice.messageservice.api.client;

import com.bakirwebservice.messageservice.api.request.BaseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "TokenService" , url = "${client.feign.token-service.path}")
public interface UserServiceClient {

    @PostMapping("${client.feign.token-service.extractUsername}")
    String extractedUsername (@RequestBody BaseRequest baseRequest);
}
