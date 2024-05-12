package com.bakirwebservice.messageservice.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "chats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Chats {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "chat_id")
    private String chatId;

    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Message> messages;

    @Column(name = "receiver")
    private String messageReceiver;

    @Column(name = "sender")
    private String messageSender;

    @Column(name = "create_date")
    private Timestamp createDate;


}
