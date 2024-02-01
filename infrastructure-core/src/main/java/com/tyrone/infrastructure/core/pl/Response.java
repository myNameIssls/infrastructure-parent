package com.tyrone.infrastructure.core.pl;

import com.tyrone.infrastructure.core.enums.ResponseCode;
import com.tyrone.infrastructure.core.enums.GlobalResponseCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class Response<D> implements Serializable {

    private String code;

    private String message;

    private D data;

    public boolean isSuccess(){
        return this.code.equals(GlobalResponseCode.SUCCESS.code());
    }

    public Response(String code, String message, D data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Response<T> success(){
        return new Response<>(GlobalResponseCode.SUCCESS.code(), GlobalResponseCode.SUCCESS.message(), null);
    }

    public static <T> Response<T> success(T data){
        return new Response<>(GlobalResponseCode.SUCCESS.code(), GlobalResponseCode.SUCCESS.message(), data);
    }

    public static <T> Response<T> fail(String code, String message){
        return new Response<>(code, message, null);
    }

    public static <T> Response<T> fail(ResponseCode responseCode){
        return new Response<>(responseCode.code(), responseCode.message(), null);
    }
}
