package com.lss.scdool.exception;

import com.lss.scdool.util.CommonResponse;
import com.lss.scdool.util.SimpleResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Magic
 * @date 15:08 2018/4/1
 * @description
 */
@ControllerAdvice

public class AllExceptionHandler {

    @ExceptionHandler(value = ScException.class)
    @ResponseBody
    public CommonResponse allException(Exception ex){
        return new SimpleResponse(1,ex.getMessage(),null);
    }

}
