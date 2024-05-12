package com.bakirwebservice.messageservice.rest.service;

import com.bakirwebservice.messageservice.api.client.UserServiceClient;
import com.bakirwebservice.messageservice.api.request.BaseRequest;
import com.bakirwebservice.messageservice.api.request.SendMessageRequest;
import com.bakirwebservice.messageservice.api.response.BaseResponse;
import com.bakirwebservice.messageservice.api.response.MessageListResponse;
import com.bakirwebservice.messageservice.api.response.MessagesResponse;
import com.bakirwebservice.messageservice.model.dto.MessageDTO;
import com.bakirwebservice.messageservice.model.dto.MessageListDTO;
import com.bakirwebservice.messageservice.model.entity.Chats;
import com.bakirwebservice.messageservice.model.entity.Message;
import com.bakirwebservice.messageservice.rest.repository.ChatsRepository;
import com.bakirwebservice.messageservice.rest.repository.MessageListRepository;
import com.bakirwebservice.messageservice.rest.service.interfaces.IMessageRestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class MessageServiceImpl implements IMessageRestService {

    private final MessageListRepository messageListRepository;

    private final ChatsRepository chatsRepository;

    private final UserServiceClient userServiceClient;

    private final MapperServiceImpl mapperService;

    public MessageListResponse messageListResponse (String chatId, BaseRequest baseRequest){
        String localUsername = userServiceClient.extractedUsername(baseRequest);
        return new MessageListResponse(mapperService.map(chatsRepository.findChatsByChatsId(chatId ,localUsername), MessageListDTO.class));
    }

    public MessagesResponse messagesResponse (String chatId, BaseRequest baseRequest){
        String localUsername = userServiceClient.extractedUsername(baseRequest);

        List<Message> messages = messageListRepository.findMessagesByChat(chatId,localUsername);
        for(int i = 0; i< messages.size(); i++){
            if(messages.get(i).getMessageSeenDate() == null && messages.get(i).getReceiver().equals(localUsername)){
                messages.get(i).setMessageSeenDate(Timestamp.from(Instant.now()));
                messageListRepository.save(messages.get(i));
            }
        }
        return new MessagesResponse(mapperService.map(messageListRepository.findMessagesByChat(chatId,localUsername), MessageDTO.class));
    }

    public BaseResponse sendMessage (SendMessageRequest request){
        String localUsername = userServiceClient.extractedUsername(request);
        Message newMessage = new Message();

        if(chatsRepository.findChatByChatMembers(localUsername, request.getSendTo()) != null){
            Chats chats = chatsRepository.findChatByChatMembers(localUsername,request.getSendTo());
            newMessage.setMessageContent(request.getMessage());
            newMessage.setMessageSentDate(Timestamp.from(Instant.now()));
            newMessage.setChat(chats);
//            newMessage.setChatsId(chats.getChatId());
            newMessage.setSender(localUsername);
            newMessage.setReceiver(request.getSendTo());

            messageListRepository.save(newMessage);
        }else{
            Chats chats = new Chats();
            chats.setMessageSender(localUsername);
            chats.setMessageReceiver(request.getSendTo());
            newMessage.setMessageContent(request.getMessage());
            newMessage.setMessageSentDate(Timestamp.from(Instant.now()));
            chats.setCreateDate(Timestamp.from(Instant.now()));
            chatsRepository.save(chats);
            newMessage.setChat(chats);
//            newMessage.setChatsId(chats.getChatId());
            newMessage.setSender(localUsername);
            newMessage.setReceiver(request.getSendTo());

            messageListRepository.save(newMessage);

        }

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setErrorDescription("MESSAGE SENDED");

        return baseResponse;
    }
}
