package com.sprinboot.jwt.app.transformer;

import com.sprinboot.jwt.app.dto.FileDTO;
import com.sprinboot.jwt.app.model.File;
import org.springframework.stereotype.Service;

@Service
public class FileTransformer {
    public File transformFileDtoToFile(FileDTO fileDTO){
        File file = new File();
        if(fileDTO == null)
            return null;
        if(fileDTO.getId() != null)
            file.setId(fileDTO.getId());
        if(fileDTO.getName() != null)
            file.setName(fileDTO.getName());
        if(fileDTO.getUrl() != null)
            file.setUrl(fileDTO.getUrl());
        return file;
    }

    public FileDTO transformFileToFileDto(File file){
        FileDTO fileDTO = new FileDTO();
        if(file == null)
            return null;
        if(file.getId() != null)
            fileDTO.setId(file.getId());
        if(file.getName() != null)
            fileDTO.setName(file.getName());
        if(file.getUrl() != null)
            fileDTO.setUrl(file.getUrl());
        return fileDTO;
    }
}
