package com.dev.ferreteriaapi.services.implement;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@CrossOrigin
public class FileUploadService {
    private static final String UPLOAD_FOLDER = "src//main//resources/images";

    public String copiFile(MultipartFile file) throws IOException {
        String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path rootPath = getPath(uniqueFileName);
        Files.copy(file.getInputStream(), rootPath);
        return uniqueFileName;
    }

    public boolean deleteFile(String deleteFile) {

        Path rootPath = getPath(deleteFile);
        File file = rootPath.toFile();

        if(file.exists() && file.canRead()) return file.delete();



        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar la imagen " + deleteFile);

    }

    public Path getPath(String filename) {
        return Paths.get(UPLOAD_FOLDER).resolve(filename).toAbsolutePath();
    }
}
