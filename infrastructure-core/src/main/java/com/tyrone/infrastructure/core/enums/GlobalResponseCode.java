package com.tyrone.infrastructure.core.enums;

public enum GlobalResponseCode implements ResponseCode {

    SUCCESS("000000", "成功"),

    SYSTEM_ERROR("100001", "系统错误"),

    PARAM_ERROR("100002", "参数错误"),

    PARAM_MISSING("100003", "参数缺失"),

    PARAM_INVALID("100004", "参数非法"),

    UNAUTHORIZED("200001", "未授权"),

    FORBIDDEN("200002", "禁止访问"),

    NOT_FOUND("300001", "资源不存在"),

    ALREADY_EXISTS("300002", "资源已存在"),

    OPERATION_FAILED("400001", "操作失败"),

    DATA_ACCESS_ERROR("400002", "数据访问错误"),

    EXTERNAL_SERVICE_ERROR("500001", "外部服务调用失败"),

    TIMEOUT("500002", "请求超时");

    private final String code;

    private final String message;

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
