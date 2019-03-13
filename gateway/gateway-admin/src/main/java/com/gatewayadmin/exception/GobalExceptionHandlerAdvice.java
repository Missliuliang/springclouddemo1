package com.gatewayadmin.exception;

import com.core.entity.vo.Result;
import com.web.Exception.DefaultGobalExceptionAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-13 17:05
 */
@RestControllerAdvice
@Slf4j
public class GobalExceptionHandlerAdvice extends DefaultGobalExceptionAdvice {

    @ExceptionHandler(value = DuplicateKeyException.class)
    public Result duplicateKeyException(DuplicateKeyException ex){
        log.error("duplicate key :{}" ,ex.getMessage());
        return Result.fail("主键冲突");
    }
}
