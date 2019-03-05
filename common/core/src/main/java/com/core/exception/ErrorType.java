package com.core.exception;

import lombok.Getter;

/**
 * @program: springclouddemo
 * @description: 错误信息类
 * @author: liu liang
 * @create: 2019-03-05 17:46
 */
@Getter
public enum  ErrorType {

    SYSTEM_ERROR("-1", "系统异常"),
    SYSTEM_BUSY("000001", "系统繁忙,请稍候再试"),
    SYSTEM_NO_PERMISSION("000002", "无权限"),

    GATEWAY_NOT_FOUND_SERVICE("010404", "服务未找到"),
    GATEWAY_ERROR("010500", "网关异常"),
    GATEWAY_CONNECT_TIME_OUT("010002", "网关超时"),

    ARGUMENT_NOT_VALUE("020000", "请求参数校验不通过"),
    UPLOAD_FILE_SIZE_LIMIT("020001", "上传文件大小超过限制");


    private String code;
    private String mesg;

    ErrorType(String code ,String mesg){
        this.code=code;
        this.mesg=mesg;
    }

}
