package com.tyrone.infrastructure.sdk.common.rpc;

import com.tyrone.infrastructure.core.pl.Response;
import com.tyrone.infrastructure.sdk.common.pl.GenCAISIdRequest;

/**
 * 自定义自动递增序列 RPC 服务接口
 */
public interface CAISRpcService {

    /**
     * 生成自定义自动递增序列ID
     *
     * @param request 请求参数
     * @return 生成的序列ID
     */
    Response<String> generate(GenCAISIdRequest request);

}