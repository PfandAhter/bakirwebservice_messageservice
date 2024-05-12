package com.bakirwebservice.messageservice.rest.controller.interfaces;

import com.bakirwebservice.messageservice.api.request.BaseRequest;
import com.bakirwebservice.messageservice.api.request.SendMessageRequest;
import com.bakirwebservice.messageservice.api.response.BaseResponse;
import com.bakirwebservice.messageservice.api.response.MessageListResponse;
import com.bakirwebservice.messageservice.api.response.MessagesResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface MessageRestServiceApi {

    @PostMapping(path = "list" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MessageListResponse> messageListResponse (@RequestParam("chatid") String chatId , @RequestBody BaseRequest baseRequest , HttpServletRequest request);

    @PostMapping(path = "received" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MessagesResponse> messagesResponse (@RequestParam("chatid") String chatId , @RequestBody BaseRequest baseRequest , HttpServletRequest request);

    @PostMapping(path = "send" , consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BaseResponse> sendMessage (@RequestBody SendMessageRequest sendMessageRequest, HttpServletRequest request);


}
