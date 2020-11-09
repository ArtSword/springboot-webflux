package com.artsword.demo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {
    /**
     * 返回值枚举
     */
    OK(200, "处理成功"),
    /**
     * 系统异常
     */
    FAILURE(500, "系统异常"),
    ;

    private int code;
    private String msg;
}
