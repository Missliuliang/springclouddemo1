package com.gatewayadmin.entity.form;

import com.core.entity.form.BaseQueryForm;
import com.gatewayadmin.entity.param.GatewayRouteQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-13 16:20
 */
@Data
@EqualsAndHashCode
@ApiModel
public class GatewayRouteQueryForm extends BaseQueryForm<GatewayRouteQueryParam> {

    @ApiModelProperty(value = "uri 路径" ,required = true)
    private String uri;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "查询开始时间必须小于当前时间")
    @ApiModelProperty(value = "查询开始时间")
    private Date createTimeStart;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "查询结束时间必须小于当前日期")
    @ApiModelProperty(value = "查询结束时间")
    private Date createdTimeEnd;
}
