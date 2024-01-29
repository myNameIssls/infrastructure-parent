package com.tyrone.infrastructure.core.enums;

public enum GlobalResponseCode implements EnumCode {

    // 正常
    SUCCESS("000000", "成功"),

    ;

    private String code;

    private String message;

    GlobalResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return null;
    }

    @Override
    public String message() {
        return null;
    }
}
