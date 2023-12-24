package com.Blogapp.exception;

import com.Blogapp.Dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotfoundHandler(ResourceNotFoundException e, WebRequest webRequest){
            ErrorDetails errorDetails=new ErrorDetails(new Date(),"802", e.getMessage(), webRequest.getDescription(false));
            return  new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> allException(Exception e,WebRequest webRequest){
        ErrorDetails newerror=new ErrorDetails(new Date(),"806",e.getMessage(),webRequest.getDescription(false));
        return  new ResponseEntity<>(newerror,HttpStatus.EXPECTATION_FAILED);
    }
}
