package com.tyrone.infrastructure.outreach.osh.remote.controller;

import com.tyrone.infrastructure.core.pl.Response;
import com.tyrone.infrastructure.outreach.osh.local.FileStorageAppService;
import com.tyrone.infrastructure.outreach.pl.FileStorageRequest;
import com.tyrone.infrastructure.outreach.pl.FileStorageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/fileStorage")
public class FileStorageController {

    private final FileStorageAppService fileStorageAppService;

    @PostMapping("/upload")
    public Response<FileStorageResponse> upload(@RequestParam("file") MultipartFile file) throws IOException {

        try {
            InputStream inputStream = file.getInputStream();

            Response<FileStorageResponse> upload = fileStorageAppService.fileStorage(new FileStorageRequest(inputStream));

            log.info("文件上传地址：{}", upload.getData().getUrl());

        } catch (IOException e) {
            log.error("", e);
        }
        return Response.success();
    }

}
