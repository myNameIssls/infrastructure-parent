package com.tyrone.infrastructure.common.ohs.local;

import com.tyrone.infrastructure.common.domain.cais.CAISService;
import com.tyrone.infrastructure.core.pl.Response;
import com.tyrone.infrastructure.sdk.common.pl.GenCAISIdRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CAISAppService {

    private final CAISService caisService;

    public Response<String> generate(GenCAISIdRequest cmd) {
        String id = caisService.generate(cmd);
        return Response.success(id);
    }

}
