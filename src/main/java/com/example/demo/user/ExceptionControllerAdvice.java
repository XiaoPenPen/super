package com.example.demo.user;

import com.example.demo.common.Result;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author xuchunpeng 2020/7/9
 */
@ControllerAdvice
public class ExceptionControllerAdvice

{

    /**

     * 对验证约束异常进行拦截，返回约定的响应体

     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result bindExceptionHandler(MethodArgumentNotValidException ex)   {

        BindingResult bindingResult = ex.getBindingResult();

        List<ObjectError> errors = bindingResult.getAllErrors();

        StringBuffer buffer = new StringBuffer();

        for (ObjectError error : errors) {

            buffer.append(error.getDefaultMessage()).append(" ");

        }

        return Result.error(buffer.toString());

    }



//    /**
//
//     * 参数类型转换错误
//
//     */
//
//    @ExceptionHandler(HttpMessageConversionException.class)
//    @ResponseBody
//    public Result parameterTypeException(HttpMessageConversionException exception) {
//
//        return Result.error(exception.getCause().getLocalizedMessage());
//
//    }

}