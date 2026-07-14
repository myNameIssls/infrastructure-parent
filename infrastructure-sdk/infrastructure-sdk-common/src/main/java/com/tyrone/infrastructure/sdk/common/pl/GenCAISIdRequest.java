package com.tyrone.infrastructure.sdk.common.pl;

import com.tyrone.infrastructure.core.domain.AbstractRequest;
import com.tyrone.infrastructure.sdk.common.domain.cais.SegmentRule;
import com.tyrone.infrastructure.sdk.common.domain.cais.SequenceType;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenCAISIdRequest extends AbstractRequest {

    private String prefix;

    private SegmentRule segmentRule;

    @Builder.Default
    private Integer initialValue = 0;

    @Builder.Default
    private Integer length = 6;

    @Builder.Default
    private String joiner = "";

    @Builder.Default
    private SequenceType sequenceType = SequenceType.DATABASE;

}
