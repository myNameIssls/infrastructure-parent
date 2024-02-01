package com.tyrone.infrastructure.common.enums;

public enum CommonResponseCode {

    ATTACHMENT_UPLOAD_FAIL("ATTACHMENT_UPLOAD_FAIL", "附件上传失败"),
    ;

    private String code;

    private String message;

    CommonResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
