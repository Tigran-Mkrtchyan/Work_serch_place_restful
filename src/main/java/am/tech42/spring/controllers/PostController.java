package am.tech42.spring.controllers;

import am.tech42.spring.dto.PostDto;
import am.tech42.spring.dto.PostHeader;
import am.tech42.spring.exception.UnknownUserException;
import am.tech42.spring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/company/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping(value = "/add")
    @PreAuthorize(value = "hasAnyAuthority('post:write')")
    public ResponseEntity<?> addPost(@RequestBody PostDto postDto) {
        try {
            postService.addPost(postDto);
        } catch (UnknownUserException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    @PreAuthorize(value = "hasAnyAuthority('post:read')")
    public ResponseEntity<List<PostHeader>> getPosts(){
        List<PostHeader> postHeaders = postService.getPosts();
        return new ResponseEntity<>(postHeaders,HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    @PreAuthorize(value = "hasAnyAuthority('post:read')")
    public ResponseEntity<PostDto> getPost( @PathVariable Integer id){
        PostDto postDto = postService.getPost(id);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }
}
