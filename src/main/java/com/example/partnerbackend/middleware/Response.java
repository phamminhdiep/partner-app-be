package com.example.partnerbackend.middleware;

import lombok.Data;

@Data
public class Response <T> {

    private int status;
    private String message;
    private T data;

    public Response(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Response(String message) {
        this.status = 500;
        this.message = message;
    }

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(200, "Success", data);
    }

    public static <T> Response<T> error(String message) {
        return new Response<>(message);
    }

    public static <T> Response<T> error(int status, String message) {
        return new Response<>(status,message);
    }
}
