package com.sprinboot.jwt.app.service.impl;

import com.sprinboot.jwt.app.config.JwtTokenUtil;
import com.sprinboot.jwt.app.dto.FileDTO;
import com.sprinboot.jwt.app.dto.PostDTO;
import com.sprinboot.jwt.app.dto.UserDTO;
import com.sprinboot.jwt.app.model.File;
import com.sprinboot.jwt.app.model.Post;
import com.sprinboot.jwt.app.model.User;
import com.sprinboot.jwt.app.repository.PostRepository;
import com.sprinboot.jwt.app.repository.UserRepository;
import com.sprinboot.jwt.app.service.FileService;
import com.sprinboot.jwt.app.service.PostService;
import com.sprinboot.jwt.app.service.UserService;
import com.sprinboot.jwt.app.transformer.PostTransformer;
import com.sprinboot.jwt.app.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostTransformer postTransformer;
    
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private UserTransformer userTransformer;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public PostDTO save(Post post) {
        Post savedPost = postRepository.save(post);
        return postTransformer.transformPostToPostDTO(savedPost);
    }

    @Override
    public Boolean save(PostDTO postDTO, String token, MultipartFile file) {
        System.out.println("in PostServiceImpl.save()");
        if (token == null)
            return null;
        // Getting username from token
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(token);
        // Get user details from username
        User user = userService.finByUsername(usernameFromToken);
        UserDTO userDTO = userTransformer.transformUserToUserDTO(user);
        // Binding user with post
        postDTO.setUser(userDTO);
        // Transforming PostDTO to post
        Post post = postTransformer.transformPostDTOToPost(postDTO);
        post.setUser(user);
        // User is transient, first save it to database
        userRepository.save(user);
        // Saving post to database
        Post savedPost = postRepository.save(post);
        // Uploading file to local storage
        File uploadedFile = fileService.upload(file);
        uploadedFile.setPost(savedPost);
        // Uploading file to database
        FileDTO uploadedFileToDatabase = fileService.save(uploadedFile);
        return uploadedFileToDatabase != null;
    }

    @Override
    public PostDTO update(Post post) {
        return null;
    }

    @Override
    public List<PostDTO> findAll() {
        List<Post> posts = postRepository.findAll();
        return postTransformer.transformPostListToPostDTOsList(posts);
    }

    @Override
    public Boolean deleteById(Long id) {
        return null;
    }

    @Override
    public PostDTO getById(Long id) {
        return null;
    }
}
