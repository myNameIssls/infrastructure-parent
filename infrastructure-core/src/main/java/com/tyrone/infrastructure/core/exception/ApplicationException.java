package com.tyrone.infrastructure.core.exception;

import com.tyrone.infrastructure.core.enums.GlobalResponseCode;
import com.tyrone.infrastructure.core.enums.ResponseCode;
import lombok.Getter;

import java.io.Serial;

@Getter
public class ApplicationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String code;

    public ApplicationException(String message) {
        super(message);
        this.code = GlobalResponseCode.SYSTEM_ERROR.code();
    }

    public ApplicationException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ApplicationException(ResponseCode responseCode) {
        super(responseCode.message());
        this.code = responseCode.code();
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
        this.code = GlobalResponseCode.SYSTEM_ERROR.code();
    }

    public ApplicationException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ApplicationException(ResponseCode responseCode, Throwable cause) {
        super(responseCode.message(), cause);
        this.code = responseCode.code();
    }
}
