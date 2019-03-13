package com.gatewayadmin.entity.form;

import com.core.entity.form.BaseForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gatewayadmin.entity.po.Filterdefinition;
import com.gatewayadmin.entity.po.GatewayRoute;
import com.gatewayadmin.entity.po.Predicatedefinition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-13 15:02
 */
@ApiModel
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class GatewayRouteForm extends BaseForm<GatewayRoute> {

    @NotEmpty(message = "网关断言不能为空")
    @ApiModelProperty(value = "网关断言")
    private List<Predicatedefinition> predicatedefinitions=new ArrayList<>();

    @ApiModelProperty(value = "网关过滤信息")
    private List<Filterdefinition> filterdefinitions=new ArrayList<>();

    @NotEmpty
    @ApiModelProperty(value = "uri不能为空")
    private String uri;

    @NotEmpty(message = "网关路由id不能为空")
    @ApiModelProperty(value = "网关路由id")
    private String routeId;

    @ApiModelProperty(value ="排序")
    private Integer order;

    @ApiModelProperty("网关路由描述")
    private String  description;

    @Override
    public GatewayRoute toPo(Class<GatewayRoute> clazz) {
        GatewayRoute gatewayRoute=new GatewayRoute();
        BeanUtils.copyProperties(this,gatewayRoute);
        try {
            ObjectMapper objectMapper=new ObjectMapper();
            gatewayRoute.setFilters(objectMapper.writeValueAsString(this.getFilterdefinitions()));
            gatewayRoute.setPredicates(objectMapper.writeValueAsString(this.getFilterdefinitions()));
        }catch (Exception e){
            log.error("网关filter和denfinition转换json失败",e);
        }
        return gatewayRoute;
    }
}
