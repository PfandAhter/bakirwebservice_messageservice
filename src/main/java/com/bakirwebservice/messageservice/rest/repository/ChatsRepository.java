package com.bakirwebservice.messageservice.rest.repository;

import com.bakirwebservice.messageservice.model.entity.Chats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatsRepository extends JpaRepository<Chats,String> {

    @Query("select c from Chats c where (c.chatId = ?1 OR ?1 = 'all') AND (c.messageReceiver = ?2 OR c.messageSender = ?2)")
    List<Chats> findChatsByChatsId (String chatsId , String localUsername);

    @Query("select c from Chats c where (c.messageReceiver = ?1 AND c.messageSender = ?2) OR (c.messageSender = ?1 AND c.messageReceiver = ?1)")
    Chats findChatByChatMembers(String sender , String receiver);


}
