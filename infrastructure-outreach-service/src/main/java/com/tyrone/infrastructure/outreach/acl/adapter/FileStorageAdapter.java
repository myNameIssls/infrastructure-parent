package com.tyrone.infrastructure.outreach.acl.adapter;

import com.tyrone.infrastructure.core.pl.Response;
import com.tyrone.infrastructure.outreach.acl.port.FileStorageClient;
import com.tyrone.infrastructure.outreach.pl.FileStorageRequest;
import com.tyrone.infrastructure.outreach.pl.FileStorageResponse;
import lombok.RequiredArgsConstructor;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileStorageAdapter implements FileStorageClient {

    private final FileStorageService fileStorageService;

    @Override
    public Response<FileStorageResponse> fileStorage(FileStorageRequest request) {

        FileInfo fileInfo = fileStorageService.of(request.getInputStream()).upload();

        FileStorageResponse response = new FileStorageResponse(fileInfo.getUrl());

        return Response.success(response);
    }

}
