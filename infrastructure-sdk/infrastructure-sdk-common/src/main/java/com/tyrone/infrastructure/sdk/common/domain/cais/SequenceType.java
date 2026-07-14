package com.tyrone.infrastructure.sdk.common.domain.cais;

public enum SequenceType {

    DATABASE("database", "数据库自增"),
    SNOWFLAKE("snowflake", "雪花算法"),
    UUID("uuid", "UUID");

    private final String code;
    private final String desc;

    SequenceType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
