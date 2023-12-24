package com.Blogapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ToString
@Data
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
   private String ResourceName;
   private String fieldName;
   private Long fieldValue;

   public ResourceNotFoundException(String ResourceName,String fieldName,Long fieldValue) {
      super(String.format("%s Not found with this %s:'%d'",ResourceName,fieldName,fieldValue));
      this.ResourceName=ResourceName;
      this.fieldName=fieldName;
      this.fieldValue=fieldValue;
   }
}
