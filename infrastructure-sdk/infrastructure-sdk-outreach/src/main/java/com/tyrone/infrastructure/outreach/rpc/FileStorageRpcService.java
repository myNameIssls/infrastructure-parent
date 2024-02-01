package com.tyrone.infrastructure.outreach.rpc;

import com.tyrone.infrastructure.core.pl.Response;
import com.tyrone.infrastructure.outreach.pl.FileStorageRequest;
import com.tyrone.infrastructure.outreach.pl.FileStorageResponse;

public interface FileStorageRpcService {

    public Response<FileStorageResponse> fileStorage(FileStorageRequest request);

}
