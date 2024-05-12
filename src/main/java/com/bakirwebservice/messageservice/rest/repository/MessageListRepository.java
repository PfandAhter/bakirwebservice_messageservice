package com.bakirwebservice.messageservice.rest.repository;

import com.bakirwebservice.messageservice.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageListRepository extends JpaRepository<Message,String> {

    @Query("select m from Message m JOIN Chats c ON c.chatId = m.chat.chatId where (c.messageSender = ?2 OR c.messageReceiver = ?2) AND m.chat.chatId = ?1")
    List<Message> findMessagesByChat (String chatId ,String localUsername);
}
