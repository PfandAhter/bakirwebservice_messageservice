package com.bakirwebservice.messageservice.rest.controller;

import com.bakirwebservice.messageservice.api.request.BaseRequest;
import com.bakirwebservice.messageservice.api.request.SendMessageRequest;
import com.bakirwebservice.messageservice.api.response.BaseResponse;
import com.bakirwebservice.messageservice.api.response.MessageListResponse;
import com.bakirwebservice.messageservice.api.response.MessagesResponse;
import com.bakirwebservice.messageservice.rest.controller.interfaces.MessageRestServiceApi;
import com.bakirwebservice.messageservice.rest.service.MessageServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "message-service")
@Slf4j

public class MessageServiceController implements MessageRestServiceApi {

    private final MessageServiceImpl messageService;

    @Override
    public ResponseEntity<MessageListResponse> messageListResponse(String chatId, BaseRequest baseRequest, HttpServletRequest request) {
        return ResponseEntity.ok(messageService.messageListResponse(chatId,baseRequest));
    }

    @Override
    public ResponseEntity<MessagesResponse> messagesResponse(String chatId, BaseRequest baseRequest, HttpServletRequest request) {
        return ResponseEntity.ok(messageService.messagesResponse(chatId,baseRequest));
    }

    @Override
    public ResponseEntity<BaseResponse> sendMessage(SendMessageRequest sendMessageRequest, HttpServletRequest request) {
        return ResponseEntity.ok(messageService.sendMessage(sendMessageRequest));
    }
}
