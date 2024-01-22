package com.tyrone.infrastructure.sdk.common.cais.enums;

public enum ShardingTypeEnum {

    NONE("NONE", "无分片", ""),
    LOCAL_DATE("LOCAL_DATE", "日期分片", "yyyyMMdd"),

    ;

    private final String code;

    private final String message;

    private String pattern;


    ShardingTypeEnum(String code, String message, String pattern) {
        this.code = code;
        this.message = message;
        this.pattern = pattern;
    }

}
