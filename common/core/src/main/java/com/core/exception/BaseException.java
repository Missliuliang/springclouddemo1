package com.core.exception;

import lombok.Getter;

/**
 * @program: springclouddemo
 * @description: 定义异常
 * @author: liu liang
 * @create: 2019-03-06 09:03
 */
@Getter
public class BaseException extends  RuntimeException {

    private ErrorType errorType;

    public BaseException(){
        this.errorType=ErrorType.SYSTEM_ERROR;
    }

    public BaseException(ErrorType errorType){
        this.errorType=errorType;
    }

    public BaseException(ErrorType errorType ,String mesg){
        super(mesg);
        this.errorType=errorType;
    }

    public BaseException(ErrorType errorType ,String mesg,Throwable cause){
        super(mesg,cause);
        this.errorType=errorType;
    }

}
