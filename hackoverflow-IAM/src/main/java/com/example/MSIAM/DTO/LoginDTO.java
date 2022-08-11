package com.example.MSIAM.DTO;

import com.example.MSIAM.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO {

    private String jwt;

    private User user;
}
