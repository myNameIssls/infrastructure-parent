package com.tyrone.infrastructure.common.ohs.remote.controller;

import com.tyrone.infrastructure.common.ohs.local.AttachmentAppService;
import com.tyrone.infrastructure.core.pl.Response;
import com.tyrone.infrastructure.sdk.common.pl.AttachmentUploadRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * 公共服务/附件
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/attachment")
public class AttachmentController {

//    private final FileStorageRpcService fileStorageRpcService;
    private final AttachmentAppService attachmentAppService;

    /**
     * 上传
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/upload")
    public Response<Void> upload(@RequestParam(name = "file") MultipartFile file, AttachmentUploadRequest request){

        try {

            String name = file.getName();
            log.info("file.getName():{}", name);
            // 0-正面.jpg
            String originalFilename = file.getOriginalFilename();
            log.info("originalFilename:{}", originalFilename);



            InputStream inputStream = file.getInputStream();
//            Response<FileStorageResponse> fileStorageResponse = fileStorageRpcService.fileStorage(new FileStorageRequest(inputStream));
//            if (!fileStorageResponse.isSuccess()) {
//                return Response.fail(fileStorageResponse.getCode(), fileStorageResponse.getMessage());
//            }





        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Response.success();
    }

}
