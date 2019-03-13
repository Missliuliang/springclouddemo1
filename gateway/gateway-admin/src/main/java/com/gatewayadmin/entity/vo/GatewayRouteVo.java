package com.gatewayadmin.entity.vo;

import com.core.entity.vo.BaseVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gatewayadmin.entity.po.Filterdefinition;
import com.gatewayadmin.entity.po.GatewayRoute;
import com.gatewayadmin.entity.po.Predicatedefinition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-13 16:54
 */
@Data
@EqualsAndHashCode
@Slf4j
public class GatewayRouteVo extends BaseVo {

    private Long id;
    private String uri;
    private Integer order;
    private List<Filterdefinition> filterdefinitions=new ArrayList<>();
    private List<Predicatedefinition> predicatedefinitions=new ArrayList<>();

    public GatewayRouteVo(GatewayRoute gatewayRoute){
        this.id=gatewayRoute.getId();
        this.uri=gatewayRoute.getUri();
        this.order=gatewayRoute.getOrder();
        ObjectMapper mapper=new ObjectMapper();
        try {
            this.filterdefinitions=mapper.readValue(gatewayRoute.getFilters(),new TypeReference<Filterdefinition>(){
            });
            this.predicatedefinitions=mapper.readValue(gatewayRoute.getPredicates(),new TypeReference<Predicatedefinition>(){
            });
        }catch (Exception e){
            log.error("网关路由对象转换失败",e);
        }
    }
}
