package com.gatewayweb.exception;

import com.core.entity.vo.Result;
import com.core.exception.ErrorType;
import io.netty.channel.ConnectTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-15 11:10
 */
@Component
@Slf4j
public class GateWayExceptionHandlerAdvice {


    @ExceptionHandler(value = ResponseStatusException.class)
    public Result handle(ResponseStatusException ex){
        log.error("response status exception:{}" ,ex.getMessage());
        return Result.fail(ErrorType.GATEWAY_ERROR);
    }


    @ExceptionHandler(value = ConnectTimeoutException.class)
    public Result handle(ConnectTimeoutException ex){
        log.error("connection timeout excetption:{}" ,ex.getMessage());
        return Result.fail(ErrorType.GATEWAY_CONNECT_TIME_OUT);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public Result handle(NotFoundException ex){
        log.error(" not found exception :{}" ,ex.getMessage());
        return Result.fail(ErrorType.GATEWAY_NOT_FOUND_SERVICE);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public Result handle(RuntimeException ex){
        log.error("runtime exception :{}" ,ex.getMessage());
        return Result.fail(ErrorType.SYSTEM_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public Result handle(Exception ex){
        log.error(" exception :{}" ,ex.getMessage());
        return Result.fail(ErrorType.SYSTEM_ERROR);
    }

    @ExceptionHandler(value = Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handle(Throwable  ex){
        Result result=Result.fail();
        if (ex instanceof ResponseStatusException) result = handle((ResponseStatusException) ex);
        if (ex instanceof RuntimeException) result=handle((RuntimeException) ex);
        if (ex instanceof NotFoundException) result =handle((NotFoundException) ex);
        if (ex instanceof ConnectTimeoutException) result =handle((ConnectTimeoutException) ex);
        if (ex instanceof Exception) result =handle((Exception) ex);
        return result;

    }







}
