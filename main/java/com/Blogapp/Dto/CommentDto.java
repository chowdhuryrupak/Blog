package com.Blogapp.Dto;

import com.Blogapp.entity.Post;
import lombok.Data;

@Data
public class CommentDto {

    private Long id;

    private String body;
    private String email;
    private String name;
}
