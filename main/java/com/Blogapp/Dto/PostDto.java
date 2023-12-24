package com.Blogapp.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {

    private long id;
    @NotNull
    @Size(min=2,message = "post title shoul have 2 charecter")
    private String title;
    @NotNull
    @Size(min = 10,message = "post descrption should have 10 chareter")
    private String description;
    private String content;
}
