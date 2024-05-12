package com.bakirwebservice.messageservice.api.response;

import com.bakirwebservice.messageservice.model.dto.MessageDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class MessagesResponse {
    List<MessageDTO> messageList;
}
