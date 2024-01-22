package com.tyrone.infrastructure.core.pl;

import com.tyrone.infrastructure.core.enums.GlobalEnumCode;

import java.io.Serializable;

public class Response<D> implements Serializable {

    private String code;

    private String message;

    private D data;

    public Response(String code, String message, D data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Response<T> success(){
        return new Response<>(GlobalEnumCode.SUCCESS.code(), GlobalEnumCode.SUCCESS.message(), null);
    }

    public static <T> Response<T> success(T data){
        return new Response<>(GlobalEnumCode.SUCCESS.code(), GlobalEnumCode.SUCCESS.message(), data);
    }

}
