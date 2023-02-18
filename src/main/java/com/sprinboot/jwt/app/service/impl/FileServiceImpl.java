package com.sprinboot.jwt.app.service.impl;

import com.sprinboot.jwt.app.dto.FileDTO;
import com.sprinboot.jwt.app.model.File;
import com.sprinboot.jwt.app.repository.FileRepository;
import com.sprinboot.jwt.app.service.FileService;
import com.sprinboot.jwt.app.transformer.FileTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.Files;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileTransformer fileTransformer;

    // Define the path for the local directory where the file will be stored

    private final Path rootPath = Paths.get("uploads");

    @Override
    public void init() {
        try {
            Files.createDirectories(rootPath);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public FileDTO save(File file) {
        File uploadedFile = fileRepository.save(file);
        return fileTransformer.transformFileToFileDto(uploadedFile);
    }

    @Override
    public File upload(MultipartFile file) {
        init();
        File uploadedFile = new File();
        try {
            Files.copy(file.getInputStream(), this.rootPath.resolve(file.getOriginalFilename()));
            uploadedFile.setName(file.getOriginalFilename());
            uploadedFile.setUrl(rootPath+"/"+file.getOriginalFilename());
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            throw new RuntimeException(e.getMessage());
        }
        return uploadedFile;
    }
}
