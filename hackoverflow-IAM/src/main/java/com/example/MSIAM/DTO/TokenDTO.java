package com.example.MSIAM.DTO;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDTO {

    private UUID authorization;

    private String jwt;
}
