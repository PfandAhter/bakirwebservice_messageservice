package com.bakirwebservice.messageservice.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "message")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "message_id")
    private String messageId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_id")
    private Chats chat;


    //TODO bunu bi checkle db de chats_id diye ayri bi column daha var neden chats_id den chat_id yaptim...
//    @Column(name = "chat_id")
//    private String chatsId;

    @Column(name = "sender")
    private String sender;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "content")
    private String messageContent;

    @Column(name = "sent_date")
    private Timestamp messageSentDate;

    @Column(name = "seen_date")
    private Timestamp messageSeenDate;


}