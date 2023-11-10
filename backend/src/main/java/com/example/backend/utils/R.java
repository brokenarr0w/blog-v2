package com.example.backend.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 湛蓝之翼
 * @version 1.0
 * @description 结果统一封装类
 * @date 2023/10/18 21:12
 */
@Data
@AllArgsConstructor
public class R<T> {
    public static final int USER_NOT_FOUND = 404;
    public static final int PASSWORD_ERROR = 500;

    int code;
    T data;
    String msg;
    public static <T> R<T> success(String msg){
        return new R<>(200,null,msg);
    }
    public static <T> R<T> success(String msg,T data){
        return new R<>(200,data,msg);
    }
    public static <T> R<T> failure(int code,String msg){
        return new R<>(code,null,msg);
    }
}
