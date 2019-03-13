package com.gatewayadmin.entity.param;

import com.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-13 16:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayRouteQueryParam extends BaseParam {

    public GatewayRouteQueryParam(String uri){
        this.uri=uri;
    }

    private String uri;

    private Date createTimeStart;

    private Date createTimeEnd;


}
