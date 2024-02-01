package com.tyrone.infrastructure.outreach.acl.port;

import com.tyrone.infrastructure.core.pl.Response;
import com.tyrone.infrastructure.outreach.pl.FileStorageRequest;
import com.tyrone.infrastructure.outreach.pl.FileStorageResponse;

public interface FileStorageClient {

    public Response<FileStorageResponse> fileStorage(FileStorageRequest request);

}
