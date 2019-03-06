package com.core.entity.vo;

import com.core.exception.BaseException;
import com.core.exception.ErrorType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.Instant;
import java.time.ZonedDateTime;


@ApiModel(description = "rest请求的返回模型，所有rest正常都返回该类的对象")
@Getter
public class Result<T> {

    public static  final  String SUCCESS_CODE="000000";
    public static  final  String SUCCESS_MESG="处理成功";

    @ApiModelProperty(value = "处理结果code", required = true)
    private String code ;

    @ApiModelProperty(value = "处理结果描述信息")
    private String mesg;

    @ApiModelProperty(value = "请求结果生成时间戳")
    private Instant timestamp ;

    @ApiModelProperty(value = "处理结果数据信息")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private  T data;

    public  Result(){
        this.timestamp= ZonedDateTime.now().toInstant();
    }

    /**
    *@Description:
    *@Param: 
    *@return: 
    *@Author: your name
    *@date: 2019/3/6
    */                                        
    public Result(ErrorType errorType){
        this.code=errorType.getCode();
        this.mesg=errorType.getMesg();
        this.timestamp=ZonedDateTime.now().toInstant();
    }

    public Result(ErrorType errorType,T data){
        this(errorType);
        this.data=data;
    }


    public Result(String code ,String mesg ,T data)
    {
        this.data=data;
        this.code=code;
        this.mesg=mesg;
        this.timestamp=ZonedDateTime.now().toInstant();
    }

    public static Result success(Object data){
        return new Result(SUCCESS_CODE ,SUCCESS_MESG,data);
    }

    public static Result success(){
        return new Result(null);
    }

    public static Result fail(){
        return new Result(ErrorType.SYSTEM_ERROR);
    }

    public  static  Result fail(BaseException baseException,Object data){
        return new Result(baseException.getErrorType(),data);
    }


    public static Result fail(BaseException baseException){
        return  fail(baseException,null);
    }

    public static Result fail(ErrorType errorType,Object data){
        return new Result(errorType,data);
    }

    public static Result fail(ErrorType errorType){
        return Result.fail(errorType,null);
    }
        /**
        *@Description: 系统错误返回异常数据
        *@Param:  data
        *@return:  result
        *@Author: your name
        *@date: 2019/3/6
        */
    public static Result fail(Object data){
        return new Result(ErrorType.SYSTEM_ERROR,data);
    }
    
    /**
    *@Description:  处理的code
    *@Param: 
    *@return: 
    *@Author: your name
    *@date: 2019/3/6
    */
    @JsonIgnore
    public boolean isSuccess(){
        return SUCCESS_CODE.equals(this.code);
    }

    @JsonIgnore
    public boolean isFail(){
        return !isSuccess();
    }




}
