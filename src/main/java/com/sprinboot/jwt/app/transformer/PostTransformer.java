package com.sprinboot.jwt.app.transformer;

import com.sprinboot.jwt.app.dto.PostDTO;
import com.sprinboot.jwt.app.dto.UserDTO;
import com.sprinboot.jwt.app.model.Post;
import com.sprinboot.jwt.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostTransformer {
    @Autowired
    private UserTransformer userTransformer;
    public PostDTO transformPostToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        if(post == null)
            return postDTO;
        if(post.getId() != null)
            postDTO.setId(post.getId());
        if(post.getTitle() != null)
            postDTO.setTitle(post.getTitle());
        if(post.getContent() != null)
            postDTO.setContent(post.getContent());
        if(post.getUser() != null){
            UserDTO userDTO = userTransformer.transformUserToUserDTO(post.getUser());
            postDTO.setUser(userDTO);
        }
        return postDTO;
    }
    public Post transformPostDTOToPost(PostDTO postDTO) {
        Post post = new Post();
        if (postDTO == null)
            return post;
        if (postDTO.getId() != null)
            post.setId(postDTO.getId());
        if (postDTO.getTitle() != null)
            post.setTitle(postDTO.getTitle());
        if (postDTO.getContent() != null)
            post.setContent(postDTO.getContent());
        if (postDTO.getUser() != null){
            User user = userTransformer.transformUserDTOToUser(postDTO.getUser());
            post.setUser(user);
        }
        return post;
    }

    public List<PostDTO> transformPostListToPostDTOsList(List<Post> postList){
        List<PostDTO> postDTOs = new ArrayList<>();
        for(Post post: postList)
            postDTOs.add(transformPostToPostDTO(post));
        return postDTOs;
    }

    public List<Post> transformPostDTOsListToPostList(List<PostDTO> postDTOs){
        List<Post> postList = new ArrayList<>();
        for(PostDTO post: postDTOs)
            postList.add(transformPostDTOToPost(post));
        return postList;
    }
}
