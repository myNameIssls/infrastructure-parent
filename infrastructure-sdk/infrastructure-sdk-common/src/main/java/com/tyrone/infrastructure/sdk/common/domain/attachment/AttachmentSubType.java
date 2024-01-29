package com.tyrone.infrastructure.sdk.common.domain.attachment;

import com.tyrone.infrastructure.core.enums.EnumCode;

public enum AttachmentSubType implements EnumCode {

    ID_CARD_FRONT(AttachmentType.ID_CARD, "ID_CARD_FRONT", "身份证正面"),
    ID_CARD_BACK(AttachmentType.ID_CARD, "ID_CARD_BACK", "身份证反面"),

    ;

    private AttachmentType attachmentType;

    private String code;

    private String message;

    AttachmentSubType(AttachmentType attachmentType, String code, String message) {
        this.attachmentType = attachmentType;
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
