package com.gatewayadmin.rest;

import com.core.entity.vo.Result;
import com.gatewayadmin.entity.form.GatewayRouteForm;
import com.gatewayadmin.entity.form.GatewayRouteQueryForm;
import com.gatewayadmin.entity.param.GatewayRouteQueryParam;
import com.gatewayadmin.entity.po.GatewayRoute;
import com.gatewayadmin.entity.vo.GatewayRouteVo;
import com.gatewayadmin.service.IGatewayRouteService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-14 10:48
 */

@RestController
@Slf4j
@RequestMapping("/gateway/routes")
@Api(value = "gateway routes api")
public class GatewayRouteController {


    @Autowired
    private IGatewayRouteService iGatewayRouteService;

    @ApiOperation(value = "新增网关路由",notes = "新增一个网关路由")
    @ApiImplicitParam(name="gatewayRouteform" ,value = "新增网关路由",required = true,
            dataType = "GatewayRouteForm",
            dataTypeClass = GatewayRouteForm.class)
    @PostMapping
    public Result add(@Valid @RequestBody GatewayRouteForm gatewayRouteForm){
        log.info("name",gatewayRouteForm);
        GatewayRoute gatewayRoute = gatewayRouteForm.toPo(GatewayRoute.class);
        return Result.success(iGatewayRouteService.add(gatewayRoute));
    }

    @ApiOperation(value = "删除网关",notes = "删除网关")
    @ApiImplicitParam(value = "网关路由id",name = "id" ,paramType = "path",required = true, dataType ="long")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id ){
        log.info("删除网关路由id:{}",id);
        iGatewayRouteService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "修改网关",notes = "修改网关")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "网关路由id",name = "id",required = true,dataType = "long"),
            @ApiImplicitParam(value = "修改网关路由id",name = "gatewayRouteForm",dataType = "GatewayRouteForm",dataTypeClass = GatewayRouteForm.class)

    })
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable Long id ,@Valid @RequestBody GatewayRouteForm gatewayRouteForm){
        GatewayRoute gatewayRoute = gatewayRouteForm.toPo(GatewayRoute.class);
        gatewayRoute.setId(id);
        iGatewayRouteService.update(gatewayRoute);
        return Result.success();
    }


    @ApiOperation(value = "获取网关信息" ,notes = "获取网关信息")
    @ApiImplicitParam(paramType = "path" ,value = "获取网关信息",name="id" ,dataType = "Long")
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable Long id){
        log.info("获取路由id:{}",id);
        return Result.success(new GatewayRouteVo(iGatewayRouteService.get(id)));
    }

    @ApiOperation(value = "uri获取网关",notes = "uri获取网关")
    @ApiImplicitParam(paramType ="query" ,value="uri获取网关", name = "name",required = true,dataType = "string")
    @ApiResponses({
            @ApiResponse(code =200 ,message = "处理成功",response = Result.class)
    })
    @GetMapping(value = "")
    public Result get(@PathVariable String uri){
        /*List<GatewayRouteVo> gatewayRouteVoList = iGatewayRouteService.query(new GatewayRouteQueryParam(uri))
                .stream().map(GatewayRouteVo::new).collect(Collectors.toList());*/

        List<GatewayRoute> gatewayRoutes = iGatewayRouteService.query(new GatewayRouteQueryParam(uri));
        List<GatewayRouteVo> gatewayRouteVoList=new ArrayList<>();
        for (GatewayRoute gatewayRoute :gatewayRoutes) {
            GatewayRouteVo gatewayRouteVo=new GatewayRouteVo(gatewayRoute);
            gatewayRouteVoList.add(gatewayRouteVo);
        }
        return Result.success(gatewayRouteVoList.stream().findFirst());

    }



    @ApiOperation(value = "根据条件搜索网关路由",notes = "搜索网关路由")
    @ApiImplicitParam(value = "网关搜索",name ="gatewayRouteQueryForm" ,required = true,dataType = "GatewayRouteQueryForm")
    @ApiResponses({
            @ApiResponse(code =200 ,message = "处理成功",response = Result.class)
    })
    @PostMapping(value = "conditions")
    public Result search(@Valid @RequestBody GatewayRouteQueryForm gatewayRouteQueryForm){
        List<GatewayRoute> gatewayRouteList = iGatewayRouteService.query(gatewayRouteQueryForm.toParam(GatewayRouteQueryParam.class));
        List<GatewayRouteVo> gatewayRouteVoList = gatewayRouteList.stream().map(GatewayRouteVo::new).collect(Collectors.toList());
        return Result.success(gatewayRouteList);
    }

    @ApiOperation(value = "重载网关路由", notes = "将所以网关的路由全部重载到redis中")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping("/overload")
    public Result overload(){
        return Result.success(iGatewayRouteService.overLoad());
    }


}
