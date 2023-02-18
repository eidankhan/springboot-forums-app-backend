package com.sprinboot.jwt.app.service;

import com.sprinboot.jwt.app.dto.PostDTO;
import com.sprinboot.jwt.app.model.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface PostService {
    PostDTO save(Post post);

    Boolean save(PostDTO postDTO, String token, MultipartFile file);

    PostDTO update(Post post);

    List<PostDTO> findAll();

    Boolean deleteById(Long id);

    PostDTO getById(Long id);


}
