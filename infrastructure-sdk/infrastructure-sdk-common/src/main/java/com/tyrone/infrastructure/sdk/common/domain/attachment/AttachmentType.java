package com.tyrone.infrastructure.sdk.common.domain.attachment;

import com.tyrone.infrastructure.core.enums.EnumCode;

public enum AttachmentType  implements EnumCode {

    ID_CARD("ID_CARD", "身份证"),

    ;

    AttachmentType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;

    @Override
    public String code() {
        return null;
    }

    @Override
    public String message() {
        return null;
    }
}
