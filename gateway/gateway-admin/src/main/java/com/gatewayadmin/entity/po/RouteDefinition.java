package com.gatewayadmin.entity.po;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-13 15:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class RouteDefinition {

    private String uri;

    private String routeId;

    private List<Filterdefinition> filterdefinitions=new ArrayList<>();

    private List<Predicatedefinition> predicatedefinitions=new ArrayList<>();

    private String description;
    private Integer order;

    private String status ;
}
