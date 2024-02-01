package com.tyrone.infrastructure.outreach.osh.remote.rpc.impl;

import com.tyrone.infrastructure.core.pl.Response;
import com.tyrone.infrastructure.outreach.osh.local.FileStorageAppService;
import com.tyrone.infrastructure.outreach.pl.FileStorageRequest;
import com.tyrone.infrastructure.outreach.pl.FileStorageResponse;
import com.tyrone.infrastructure.outreach.rpc.FileStorageRpcService;
import lombok.RequiredArgsConstructor;

//@DubboService
@RequiredArgsConstructor
public class FileStorageRpcServiceImpl implements FileStorageRpcService {

    private final FileStorageAppService fileStorageAppService;

    @Override
    public Response<FileStorageResponse> fileStorage(FileStorageRequest request) {
        return fileStorageAppService.fileStorage(request);
    }
}
