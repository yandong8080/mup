package com.java.servicebase.hanler;

import com.java.commonutils.api.APICODE;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * 全局异常处理类
 *
 * @version V1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(java.lang.Exception.class)
    @ResponseBody
    public APICODE errorHandler(java.lang.Exception e){
        e.printStackTrace();
        return APICODE.ERROR().message("Exception:服务器异常");
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public APICODE errorHandler(MyException e) {
        e.printStackTrace();
        return APICODE.ERROR().message(e.getMsg()).code(e.getCode());
    }

}
