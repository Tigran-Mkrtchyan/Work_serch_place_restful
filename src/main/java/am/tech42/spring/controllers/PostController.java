package am.tech42.spring.controllers;

import am.tech42.spring.dto.PostDto;
import am.tech42.spring.dto.PostHeader;
import am.tech42.spring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/company/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addPost(@RequestBody PostDto postDto) {
        postService.addPost(postDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<PostHeader>> getPosts(){
        List<PostHeader> postHeaders = postService.getPosts();
        return new ResponseEntity<>(postHeaders,HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDto> getPost( @PathVariable Integer id){
        PostDto postDto = postService.getPost(id);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }
}
