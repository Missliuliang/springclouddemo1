package com.gatewayadmin.entity.po;


import com.core.entity.po.BasePo;
import lombok.*;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-13 15:39
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class GatewayRoute extends BasePo {

    private String uri;

    private String routeId;
    private String predicates;
    private String filters;
    private String description;
    private Integer order=0;
    private String status="Y";
}
