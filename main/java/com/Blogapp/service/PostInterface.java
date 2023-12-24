package com.Blogapp.service;

import com.Blogapp.Dto.PostDto;
import com.Blogapp.Dto.PostResponse;

import java.util.List;

public interface PostInterface {


    PostDto saveOnepost(PostDto dto);

    void deleteOnepost(long id);

    PostDto updatepost(PostDto dto, Long id);

    PostDto getonepostByID(long id);

    List<PostDto> getAllpost();

    PostResponse getAllpost(int Pageno, int PageSize,String sortBy,String sortDir);
}
