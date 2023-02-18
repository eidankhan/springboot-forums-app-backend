package com.sprinboot.jwt.app.controller;

import com.sprinboot.jwt.app.dto.PostDTO;
import com.sprinboot.jwt.app.service.PostService;
import com.sprinboot.jwt.app.transformer.PostTransformer;
import com.sprinboot.jwt.app.util.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostTransformer postTransformer;

    @GetMapping
    public List<PostDTO> findAll() {
        System.out.println("in PostController.findAll()");
        return postService.findAll();
    }

    @PostMapping
    public GenericResponse save(HttpServletRequest request,
                                @RequestParam("title") String title,
                                @RequestParam("content") String content,
                                @RequestParam("file") MultipartFile file) {
        System.out.println("in PostController.save()");
        PostDTO post = new PostDTO(title, content);
        String token = request.getHeader("Authorization");
        // Extract the token from the header as header has Bearer
        token = token.split(" ")[1];
        Boolean savedPost = postService.save(post, token, file);
        if (savedPost)
            return new GenericResponse(200, "Post saved successfully");
        return new GenericResponse(500, "Unable to save post");
    }
}
