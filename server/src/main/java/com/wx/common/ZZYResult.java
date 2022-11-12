package com.wx.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zzy
 * @desc 统一返回结构
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ZZYResult<T> {
    private Integer statusCode;
    private String message;
    private T data;


    public static <T> ZZYResult<T> getInstance(Integer statusCode,String message,T data) {
        return new ZZYResult<>(statusCode, message, data);
    }

}
