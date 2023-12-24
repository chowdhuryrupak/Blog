package com.Blogapp.service;

import com.Blogapp.Dto.PostDto;
import com.Blogapp.Dto.PostResponse;
import com.Blogapp.entity.Post;
import com.Blogapp.exception.ResourceNotFoundException;
import com.Blogapp.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService implements PostInterface{
    @Autowired
    private  PostRepo postRepo;

    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public Post convertDtoToEntity(PostDto dto){
        Post newpost=new Post();
        newpost.setTitle(dto.getTitle());
        newpost.setContent(dto.getContent());
        newpost.setDescription(dto.getDescription());
        return newpost;
    }
    public PostDto convertEntityToDto(Post post){
        PostDto dto=new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }


    @Override
    public PostDto saveOnepost(PostDto dto) {
        Post post=convertDtoToEntity(dto);
        Post savedpost = postRepo.save(post);
        PostDto saveddto=convertEntityToDto(savedpost);
        return saveddto;
    }

    @Override
    public void deleteOnepost(long id) {
            Post post=postRepo.findById(id).orElseThrow(
                    ()->new ResourceNotFoundException("POst","id",id)
            );
            postRepo.deleteById(id);
    }

    @Override
    public PostDto updatepost(PostDto dto,Long id) {
            Post post=postRepo.findById(id).orElseThrow(
                    ()->new ResourceNotFoundException("Post","id",id)
            );

            post.setTitle(dto.getTitle());
            post.setContent(dto.getContent());
            post.setDescription(dto.getDescription());
            Post newpost = postRepo.save(post);
            PostDto updateddto=convertEntityToDto(newpost);
        return updateddto;

    }

    @Override
    public PostDto getonepostByID(long id) {
        Post post=postRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("","",id)
        );
        PostDto getdto= convertEntityToDto(post);
        return getdto;
    }

    @Override
    public List<PostDto> getAllpost() {
        List<Post> postList=postRepo.findAll();
        List<PostDto> postDtoList=postList.stream().map(post -> convertEntityToDto(post)).collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public PostResponse getAllpost(int Pageno, int PageSize, String sortBy,String sortDir) {
       Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(Pageno,PageSize,sort);
        Page<Post> listallpost = postRepo.findAll(pageable);
        List<Post> contentlist=listallpost.getContent();

        List<PostDto> contents= contentlist.stream().map(post -> convertEntityToDto(post)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(contents);
        postResponse.setPageNo(listallpost.getNumber());
        postResponse.setPageSize(listallpost.getSize());
        postResponse.setLast(listallpost.isLast());
        postResponse.setTotalPages(listallpost.getTotalPages());
        postResponse.setTotalElements(listallpost.getTotalElements());

        return  postResponse;
    }
}
