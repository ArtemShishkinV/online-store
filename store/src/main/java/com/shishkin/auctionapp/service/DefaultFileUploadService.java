package com.shishkin.auctionapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class DefaultFileUploadService implements FileUploadService {
    private static final String UPLOAD_PATH = "./src/main/resources/static/images/uploads/";

    @Override
    public String uploadFile(String uploadDir, MultipartFile uploadFile) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_PATH, uploadDir);
        String imagePath = Objects.requireNonNull(uploadFile.getOriginalFilename());

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = uploadFile.getInputStream()) {
            Path filePath = uploadPath.resolve(imagePath);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + imagePath, ioe);
        }

        return uploadFile.getOriginalFilename();
    }

}
