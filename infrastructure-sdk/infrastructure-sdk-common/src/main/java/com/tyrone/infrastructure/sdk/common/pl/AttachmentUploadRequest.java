package com.tyrone.infrastructure.sdk.common.pl;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AttachmentUploadRequest implements Serializable {

    private String type;

}
