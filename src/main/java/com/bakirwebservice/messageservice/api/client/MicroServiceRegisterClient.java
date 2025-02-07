package com.bakirwebservice.messageservice.api.client;

import com.bakirwebservice.messageservice.api.request.MicroServiceReadyRequest;
import com.bakirwebservice.messageservice.api.request.MicroServiceStoppedRequest;
import com.bakirwebservice.messageservice.api.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "MicroServiceRegister" , url = "${client.feign.microServiceRegister-service.path}")
public interface MicroServiceRegisterClient {

    @PostMapping("${client.feign.microServiceRegister-service.ready}")
    BaseResponse microServiceReady (@RequestBody MicroServiceReadyRequest microServiceReadyRequest);

    @PostMapping("${client.feign.microServiceRegister-service.stopped}")
    BaseResponse microServiceStopped (@RequestBody MicroServiceStoppedRequest microServiceStoppedRequest);
}

