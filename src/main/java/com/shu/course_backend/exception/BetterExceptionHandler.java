package com.shu.course_backend.exception;

import com.shu.course_backend.model.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
* @program: ExceptionHandler
* @Description: 错误处理
* @Author: SoCMo
* @Date: 2021/4/28
*/
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class BetterExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleConstraintViolationException(ConstraintViolationException e) {
        return Result.error(EmAllException.BAD_REQUEST, e.getMessage());
    }
}
