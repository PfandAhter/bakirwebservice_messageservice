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

    private String microServiceCode;

    private static final String microServiceName = "MESSAGE-SERVICE";

    @EventListener(ApplicationReadyEvent.class)
    public void logToDataBaseServiceReady(){
        microServiceCode = Util.generateCode();
        MicroServiceReadyRequest microServiceReadyRequest = new MicroServiceReadyRequest();
        microServiceReadyRequest.setMicroServiceCode(microServiceCode);
        microServiceReadyRequest.setMicroServiceStatus("UP");
        microServiceReadyRequest.setMicroServiceErrorCode("91000");
        microServiceReadyRequest.setMicroServiceReadyDate(Timestamp.from(Instant.now()));
        microServiceReadyRequest.setMicroServiceName(microServiceName);

        microServiceRegisterClient.microServiceReady(microServiceReadyRequest);
    }

    @PreDestroy
    public void testLogToDatabaseStopped(){
        MicroServiceStoppedRequest microServiceStoppedRequest = new MicroServiceStoppedRequest();
        microServiceStoppedRequest.setMicroServiceStoppedDate(Timestamp.from(Instant.now()));
        microServiceStoppedRequest.setMicroServiceName(microServiceName);
        microServiceStoppedRequest.setMicroServiceErrorCode("91000");
        microServiceStoppedRequest.setMicroServiceStatus("DOWN");
        microServiceStoppedRequest.setMicroServiceCode(microServiceCode);

        microServiceRegisterClient.microServiceStopped(microServiceStoppedRequest);
    }
}
