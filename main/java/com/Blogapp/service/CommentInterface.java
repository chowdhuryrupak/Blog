package com.Blogapp.service;

import com.Blogapp.Dto.CommentDto;
import com.Blogapp.entity.Comment;

import java.util.List;

public interface CommentInterface {

    public CommentDto saveComment(long postId, CommentDto commentDto);

    List<CommentDto> getAllcommentByPostId(long postId);

    CommentDto UpdateCommentByPostidandcommentId(long postId, long commentId, CommentDto commemtDto);

    void deleteCommentByPostidandcommentId(long postId, long commentId);
}
