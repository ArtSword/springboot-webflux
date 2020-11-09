package com.artsword.demo.common;


import lombok.*;

import java.io.Serializable;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class Result<T> implements Serializable {
    private boolean success;
    private String message;
    private int code;
    private T data;

    private Integer pageNo;
    private Integer pageSize;
    private Long totalCount;
    private Integer totalPage;

    public static <T> Result<T> success(T data) {
        return Result.<T>builder()
                .success(true)
                .code(ResultCodeEnum.OK.getCode())
                .message(ResultCodeEnum.OK.getMsg())
                .data(data)
                .build();
    }

    public static Result success() {
        return success(null);
    }

    public static Result failure(ResultCodeEnum resultCodeEnum) {
        return Result.builder()
                .success(false)
                .code(resultCodeEnum.getCode())
                .message(resultCodeEnum.getMsg())
                .build();
    }


    public static Result failure(String message) {
        return Result.builder()
                .success(false)
                .code(ResultCodeEnum.FAILURE.getCode())
                .message(message)
                .build();
    }

    public static Result failure(Integer code, String message) {
        return Result.builder()
                .success(false)
                .code(code)
                .message(message)
                .build();
    }

    public static Result failure(ResultCodeEnum resultCodeEnum, Object data) {
        return Result.builder()
                .success(false)
                .code(resultCodeEnum.getCode())
                .message(resultCodeEnum.getMsg())
                .data(data)
                .build();
    }
}
