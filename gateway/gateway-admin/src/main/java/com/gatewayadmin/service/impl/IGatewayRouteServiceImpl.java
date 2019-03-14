package com.gatewayadmin.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gatewayadmin.dao.GatewayRouteMapper;
import com.gatewayadmin.entity.param.GatewayRouteQueryParam;
import com.gatewayadmin.entity.po.GatewayRoute;
import com.gatewayadmin.entity.vo.GatewayRouteVo;
import com.gatewayadmin.service.IGatewayRouteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-14 09:52
 */
@Slf4j
@Service
public class IGatewayRouteServiceImpl implements IGatewayRouteService {

    String GATEWAY_ROUTES="gateway_routes::";

    @Autowired
    GatewayRouteMapper gatewayRouteMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private String toJson(GatewayRouteVo gatewayRouteVo){
        String routeDefinitionJson = Strings.EMPTY;
        try{
                routeDefinitionJson=new ObjectMapper().writeValueAsString(gatewayRouteVo);
        }catch (JsonProcessingException e){
            log.error("网关对象序列化为json string",e);
        }
        return routeDefinitionJson;
    }

    @Override
    public GatewayRoute get(Long id) {
        return gatewayRouteMapper.selectByone(id);
    }

    @Override
    public Long add(GatewayRoute gatewayRoute) {
        Long insert = gatewayRouteMapper.insert(gatewayRoute);
        stringRedisTemplate.opsForValue().set(GATEWAY_ROUTES+gatewayRoute.getId(),toJson(new GatewayRouteVo(gatewayRoute)));
        return insert;
    }

    @Override
    public List<GatewayRoute> query(GatewayRouteQueryParam gatewayRouteQueryParam) {
        return gatewayRouteMapper.query(gatewayRouteQueryParam);
    }

    @Override
    public void update(GatewayRoute gatewayRoute) {
        //先更新数据库
        gatewayRouteMapper.update(gatewayRoute);
        //gateway_routes::124
        //stringRedisTemplate.opsForValue().get(GATEWAY_ROUTES+gatewayRoute.getId());
        //首先删除redis 中原来的记录 然后在通过id到数据库查询出最新的记录放到redis中
        stringRedisTemplate.delete(GATEWAY_ROUTES+gatewayRoute.getId());
        stringRedisTemplate.opsForValue().set(GATEWAY_ROUTES+gatewayRoute.getId(),
                toJson(new GatewayRouteVo(get(gatewayRoute.getId()))));

    }

    @Override
    public void delete(Long id) {
        gatewayRouteMapper.delete(id);
        stringRedisTemplate.delete(GATEWAY_ROUTES+id);
    }

    @Override
    public Boolean overLoad() {
        List<GatewayRoute> query = gatewayRouteMapper.query(new GatewayRouteQueryParam());
        ValueOperations valueOperations=stringRedisTemplate.opsForValue();
        query.forEach(gatewayRoute -> {
            valueOperations.set(GATEWAY_ROUTES+gatewayRoute.getId(),toJson(new GatewayRouteVo(gatewayRoute)));
        });
        return true     ;
    }
}
