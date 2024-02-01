package com.tyrone.infrastructure.core.enums;

public enum GlobalResponseCode implements ResponseCode {

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
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
