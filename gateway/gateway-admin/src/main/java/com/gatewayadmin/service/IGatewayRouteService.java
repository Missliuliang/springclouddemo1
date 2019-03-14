package com.gatewayadmin.service;

import com.gatewayadmin.entity.param.GatewayRouteQueryParam;
import com.gatewayadmin.entity.po.GatewayRoute;

import java.util.List;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-14 09:47
 */

public interface IGatewayRouteService {

    GatewayRoute get(Long id);

    Long add(GatewayRoute gatewayRoute);

    List<GatewayRoute> query(GatewayRouteQueryParam gatewayRouteQueryParam);

    void update(GatewayRoute gatewayRoute);

    void delete(Long id);

    /**
     * 重新加载网关路由配置到redis
     * @return 成功返回true
     */
    Boolean overLoad();
}
