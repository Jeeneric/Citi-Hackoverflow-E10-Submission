package com.example.Knowledge.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleDTO {
    private String name;
    private String date;
    private String article;
    private String image;

}
