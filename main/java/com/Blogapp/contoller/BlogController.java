package com.Blogapp.contoller;

import com.Blogapp.Dto.PostDto;
import com.Blogapp.Dto.PostResponse;
import com.Blogapp.service.PostInterface;
import com.Blogapp.utils.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
   private PostInterface postInterface;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/postSave")
    public ResponseEntity<?> saveOnePost(@Valid
            @RequestBody PostDto dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto savedto = postInterface.saveOnepost(dto);
        return new ResponseEntity<PostDto>(savedto, HttpStatus.CREATED);
    }

    @GetMapping ("/GetAllpost")
    public ResponseEntity<List<PostDto>>saveOnePost() {
        List<PostDto> Allsavedto = postInterface.getAllpost();
        return new ResponseEntity<List<PostDto>>(Allsavedto, HttpStatus.ACCEPTED);
    }
    @GetMapping ("/GetAllpostperpage")
    public PostResponse geteAllPost(@RequestParam (value = "pageNo",defaultValue = AppConstant.DEFAULT_PAGE_NUMBER,required = false)int pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE,required = false)int pageSize,
                                    @RequestParam(value="sortBy",defaultValue=AppConstant.DEFAULT_SORT_BY,required=false) String sortBy ,
                                    @RequestParam(value = "sortDir",defaultValue = AppConstant.DEFAULT_SORT_DIR,required = false)String sortDir ) {
        PostResponse Allsavedto = postInterface.getAllpost(pageNo,pageSize,sortBy,sortDir);

        return Allsavedto;
    }

    @GetMapping ("/post/{pid}")
    public ResponseEntity<PostDto>getOnePost(@PathVariable("pid") long id) {
        PostDto getdto = postInterface.getonepostByID(id);
        return new ResponseEntity<PostDto>(getdto, HttpStatus.ACCEPTED);
    }

    @PutMapping ("/Updatepost/{pid}")
    public ResponseEntity<PostDto>updateOnePost(@RequestBody PostDto dto, @PathVariable("pid") long id) {
        PostDto newdto = postInterface.updatepost(dto,id);
        return new ResponseEntity<PostDto>(newdto, HttpStatus.OK);
    }

    @DeleteMapping ("/deletepost/{pid}")
    public ResponseEntity<?>updateOnePost( @PathVariable("pid") long id) {
         postInterface.deleteOnepost(id);
        return new ResponseEntity<String>("corresponding post Delete..succesfully with id:"+id, HttpStatus.OK);
    }
}
