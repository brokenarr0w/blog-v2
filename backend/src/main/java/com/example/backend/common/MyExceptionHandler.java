package com.example.backend.common;

import cn.dev33.satoken.exception.NotLoginException;
import com.example.backend.utils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 湛蓝之翼
 * @version 1.0
 * @description 全局异常处理
 * @date 2023/10/30 18:58
 */
@ControllerAdvice

public class MyExceptionHandler {
    @ExceptionHandler(value = NotLoginException.class)
    @ResponseBody
    public R<Object> tokenNotFound(Exception e){
        return R.failure(400,"没有登录不能访问后台页面");
    }
}
