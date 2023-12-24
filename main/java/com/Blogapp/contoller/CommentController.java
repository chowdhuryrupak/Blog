package com.Blogapp.contoller;

import com.Blogapp.Dto.CommentDto;
import com.Blogapp.service.CommentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentInterface commentInterface;

    @PostMapping("/{postid}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postid")long postid, @RequestBody CommentDto commentdto){
       CommentDto dto =commentInterface.saveComment(postid,commentdto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{postid}/commets")

    public ResponseEntity<List<CommentDto>> getAllcommentByPostid(@PathVariable("postid") long id){
        List<CommentDto> lisComment =commentInterface.getAllcommentByPostId(id);

        return  new ResponseEntity<List<CommentDto>>(lisComment,HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId")long postId,
                                                    @PathVariable("commentId") long commentId,
                                                    @RequestBody CommentDto commemtDto    )     {
        CommentDto updatedcommment=commentInterface.UpdateCommentByPostidandcommentId(postId,commentId,commemtDto);

        return  new ResponseEntity<CommentDto>(updatedcommment,HttpStatus.OK);
    }


    @DeleteMapping("/posts/{postId}/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("postId")long postId,
                                                    @PathVariable("commentId") long commentId
                                                     )     {
        commentInterface.deleteCommentByPostidandcommentId(postId,commentId);

        return  new ResponseEntity<String>("Comment is deleted Succesfully",HttpStatus.OK);
    }
}
