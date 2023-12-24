package com.Blogapp.service;

import com.Blogapp.Dto.CommentDto;
import com.Blogapp.entity.Comment;
import com.Blogapp.entity.Post;
import com.Blogapp.exception.ResourceNotFoundException;
import com.Blogapp.repository.CommentRepo;
import com.Blogapp.repository.PostRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService implements  CommentInterface{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;


    public CommentDto saveComment(long postId,CommentDto commentDto){
        Post post=postRepo.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("post","id",postId)
        );
        Comment comment=modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);

        Comment newcomment=commentRepo.save(comment);

        CommentDto commentDto1=modelMapper.map(newcomment,CommentDto.class);

        return  commentDto1;
    }

    @Override
    public List<CommentDto> getAllcommentByPostId(long postId) {
        List<Comment> commentList=commentRepo.findByPostId(postId);
        List<CommentDto> dtolist=commentList.stream().map(commment ->modelMapper.map(commment,CommentDto.class)).collect(Collectors.toList());

        return dtolist;
    }

    @Override
    public CommentDto UpdateCommentByPostidandcommentId(long postId, long commentId, CommentDto commemtDto) {
        Post post=postRepo.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("post","id",postId)
        );
        Comment comment=commentRepo.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("comment","id",commentId)
        );
        comment.setName(commemtDto.getName());
        comment.setEmail(commemtDto.getEmail());
        comment.setBody(commemtDto.getBody());
        Comment updatedcomment=commentRepo.save(comment);
        CommentDto updateddto=modelMapper.map(updatedcomment,CommentDto.class);
        return updateddto;
    }

    @Override
    public void deleteCommentByPostidandcommentId(long postId, long commentId) {
        Post post=postRepo.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("post","id",postId)
        );
        Comment comment=commentRepo.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("comment","id",commentId)
        );
        commentRepo.deleteById(commentId);

    }

//    public Comment commentDtoToEntity(CommentDto commentDto){
//        Comment newComment=new Comment();
//        newComment.setName(commentDto.getName());
//        newComment.setBody(commentDto.getBody());
//        newComment.setEmail(commentDto.getEmail());
//        return newComment;
//    }

//    public CommentDto EntitytoDTo(Comment comment){
//        CommentDto commentDto=new CommentDto();
//            commentDto.setId(comment.getId());
//            commentDto.setBody(comment.getBody());
//            commentDto.setEmail(comment.getEmail());
//            commentDto.setName(comment.getName());
//
//        return commentDto;
//    }

}
