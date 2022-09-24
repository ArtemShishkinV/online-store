package com.shishkin.auctionapp.service;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    String uploadFile(String userPath, MultipartFile uploadFile) throws IOException;
}
