package com.example.mail.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

/**
 *Сущность посылки
 *Используються аннотации Lombok для лучшей читаемости кода
 */
@Entity
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Departure {
    private Long id;
    private String type;
    private Integer senderIndex;
    private String recipientAddress;
    private String recipientName;
    private Integer recipientIndex;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    @Basic
    @Column(name = "sender_index")
    public Integer getSenderIndex() {
        return senderIndex;
    }

    @Basic
    @Column(name = "recipient_address")
    public String getRecipientAddress() {
        return recipientAddress;
    }

    @Basic
    @Column(name = "recipient_name")
    public String getRecipientName() {
        return recipientName;
    }

    @Basic
    @Column(name = "recipient_index")
    public Integer getRecipientIndex() {
        return recipientIndex;
    }
}
