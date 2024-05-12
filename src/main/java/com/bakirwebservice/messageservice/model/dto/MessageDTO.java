package com.bakirwebservice.messageservice.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MessageDTO {
    private String chatsId;

    private String receiver;

    private String sender;

    private String messageContent;

    private String messageSentDate;

    private String messageSeenDate;
}
