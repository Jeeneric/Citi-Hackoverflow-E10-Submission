package com.example.MSIAM.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;

@Table(name = "token_mapping")
@Entity
@Data
public class Token {

    @Override
    public String toString() {
        return "Token {" +
                "authorization='" + authorization + '\'' +
                ", jwt='" + jwt + '\'' +
                '}';
    }

    @Id
    @Column(name = "authorizationtoken")
    @Type(type = "uuid-char")
    private UUID authorization = UUID.randomUUID();

    private String jwt;
}
