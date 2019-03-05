package com.core.entity.vo;

import com.core.exception.ErrorType;
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

   /* public Result(ErrorType errorType,T data){
        this(errorType);
        this.data=data;
    }*/


}
