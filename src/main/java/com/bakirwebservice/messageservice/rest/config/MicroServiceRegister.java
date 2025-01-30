package com.bakirwebservice.messageservice.rest.config;

import com.bakirwebservice.messageservice.api.client.MicroServiceRegisterClient;
import com.bakirwebservice.messageservice.api.request.MicroServiceReadyRequest;
import com.bakirwebservice.messageservice.api.request.MicroServiceStoppedRequest;
import com.bakirwebservice.messageservice.rest.util.Util;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;


@Service
@RequiredArgsConstructor
@Component
@Slf4j

public class MicroServiceRegister {
    private final MicroServiceRegisterClient microServiceRegisterClient;

    private static final String microServiceCode = "MSc371ea389ce74caea1d595d5d3de2f53";

    private static final String microServiceName = "MESSAGE-SERVICE";

    @EventListener(ApplicationReadyEvent.class)
    public void logToDataBaseServiceReady(){
        MicroServiceReadyRequest microServiceReadyRequest = new MicroServiceReadyRequest();
        microServiceReadyRequest.setServiceCode(microServiceCode);
        microServiceReadyRequest.setServiceStatus("UP");
        microServiceReadyRequest.setErrorCode("91000");
        microServiceReadyRequest.setServiceReadyDate(Timestamp.from(Instant.now()));
        microServiceReadyRequest.setServiceName(microServiceName);

        microServiceRegisterClient.microServiceReady(microServiceReadyRequest);
    }

    @PreDestroy
    public void testLogToDatabaseStopped(){
        MicroServiceStoppedRequest microServiceStoppedRequest = new MicroServiceStoppedRequest();
        microServiceStoppedRequest.setServiceStoppedDate(Timestamp.from(Instant.now()));
        microServiceStoppedRequest.setServiceName(microServiceName);
        microServiceStoppedRequest.setErrorCode("91000");
        microServiceStoppedRequest.setServiceStatus("DOWN");
        microServiceStoppedRequest.setServiceCode(microServiceCode);

        microServiceRegisterClient.microServiceStopped(microServiceStoppedRequest);
    }
}
