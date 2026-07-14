package com.tyrone.infrastructure.common.ohs.remote;

import com.tyrone.infrastructure.common.ohs.local.CAISAppService;
import com.tyrone.infrastructure.core.pl.Response;
import com.tyrone.infrastructure.sdk.common.pl.GenCAISIdRequest;
import com.tyrone.infrastructure.sdk.common.rpc.CAISRpcService;
import lombok.RequiredArgsConstructor;

/**
 * CAIS RPC 服务实现
 */
@RequiredArgsConstructor
public class CAISRpcServiceImpl implements CAISRpcService {

    private final CAISAppService caisAppService;

    @Override
    public Response<String> generate(GenCAISIdRequest request) {
        return caisAppService.generate(request);
    }

}