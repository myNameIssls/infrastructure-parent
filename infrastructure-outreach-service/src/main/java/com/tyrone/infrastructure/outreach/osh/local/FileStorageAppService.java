package com.tyrone.infrastructure.outreach.osh.local;

import com.tyrone.infrastructure.core.pl.Response;
import com.tyrone.infrastructure.outreach.acl.port.FileStorageClient;
import com.tyrone.infrastructure.outreach.pl.FileStorageRequest;
import com.tyrone.infrastructure.outreach.pl.FileStorageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileStorageAppService {

    private final FileStorageClient fileStorageClient;

    public Response<FileStorageResponse> fileStorage(FileStorageRequest request){
        return fileStorageClient.fileStorage(request);
    }

}
