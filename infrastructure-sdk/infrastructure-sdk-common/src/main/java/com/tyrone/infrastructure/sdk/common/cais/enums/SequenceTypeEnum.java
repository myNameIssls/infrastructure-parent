package com.tyrone.infrastructure.sdk.common.cais.enums;

import com.tyrone.infrastructure.core.enums.EnumCode;

public enum SequenceTypeEnum implements EnumCode {

    SIX_INT_AUTO_INCREASE("SIX_INT_AUTO_INCREASE", "6位整型自增", 6),

    ;

    private String code;

    private String message;

    private Integer length;

    SequenceTypeEnum(String code, String message, Integer length) {
        this.code = code;
        this.message = message;
        this.length = length;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
