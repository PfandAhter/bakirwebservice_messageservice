package com.bakirwebservice.messageservice.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SendMessageRequest extends BaseRequest{
    private String sendTo;
    private String message;
}
