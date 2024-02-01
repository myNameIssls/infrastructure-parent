package com.tyrone.infrastructure.sdk.common.pl;

import com.tyrone.infrastructure.core.domain.AbstractRequest;
import com.tyrone.infrastructure.sdk.common.domain.cais.SegmentRule;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenCAISIdRequest extends AbstractRequest {

    private String prefix;

    private SegmentRule segmentRule;

    @Builder.Default
    private Integer initialValue = 0;

    @Builder.Default
    private Integer length = 6;

    @Builder.Default
    private String joiner = "";

}
