package com.tyrone.infrastructure.sdk.common.cais.pl;

import com.tyrone.infrastructure.core.domain.AbstractCommand;
import com.tyrone.infrastructure.sdk.common.cais.domain.SegmentRule;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenCAISIdCommand extends AbstractCommand {

    private String prefix;

    private SegmentRule segmentRule;

    @Builder.Default
    private Integer initialValue = 0;

    @Builder.Default
    private Integer length = 6;

    @Builder.Default
    private String joiner = "";

}
