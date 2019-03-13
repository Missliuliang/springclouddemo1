package com.web.Exception;

import com.core.entity.vo.Result;
import com.core.exception.BaseException;
import com.core.exception.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-12 13:49
 */

@Slf4j
public class DefaultGobalExceptionAdvice {


    /**
    *@Description:  参数异常
    *@Param:
    *@return:
    *@Author: your name
    *@date: 2019/3/13
    */
    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public Result missingServletRequestParamterException(MissingServletRequestParameterException ex){
        log.error("missing servlet request parameter exception:{}", ex.getMessage());
        return Result.fail(ErrorType.GATEWAY_ERROR);
    }

    @ExceptionHandler(value = {MultipartException.class})
    public Result uploadLimitException(MultipartException ex){
        log.error("upload file size limit:{}" ,ex.getMessage());
        return Result.fail(ErrorType.UPLOAD_FILE_SIZE_LIMIT);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result serviceException(MethodArgumentNotValidException ex){
        log.error("service arg exception:{}" ,ex.getMessage());
        return Result.fail(ErrorType.GATEWAY_ERROR);
    }

    @ExceptionHandler(value = BaseException.class)
    public Result baseException(BaseException ex){
        log.error("base exception:{}" ,ex.getMessage());
        return Result.fail(ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exception(){
        return Result.fail();
    }

    @ExceptionHandler(value = Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result throwable(){
        return Result.fail();
    }




}
