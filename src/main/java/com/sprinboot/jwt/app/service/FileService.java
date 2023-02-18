package com.sprinboot.jwt.app.service;

import com.sprinboot.jwt.app.dto.FileDTO;
import com.sprinboot.jwt.app.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {
    FileDTO save(File file);

    void init();

    File upload(MultipartFile file);
}
