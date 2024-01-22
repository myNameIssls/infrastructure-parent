package com.tyrone.infrastructure.sdk.common.cais.enums;

public enum CodeTypeEnum {

    RZ("RZ", "融资编号", ShardingTypeEnum.LOCAL_DATE, SequenceTypeEnum.SIX_INT_AUTO_INCREASE),

    ;

    private final String code;

    private final String message;

    private ShardingTypeEnum shardingType;

    private SequenceTypeEnum sequenceType;

    CodeTypeEnum(String code, String message, ShardingTypeEnum shardingType, SequenceTypeEnum sequenceType) {
        this.code = code;
        this.message = message;
        this.shardingType = shardingType;
        this.sequenceType = sequenceType;
    }
}
