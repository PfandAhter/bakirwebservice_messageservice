package com.bakirwebservice.messageservice.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter

public class MessageListDTO {
    private String chatId;
    private String messageReceiver;
    private String messageSender;
    private Timestamp createDate;
}
