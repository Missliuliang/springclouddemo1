package com.gatewayadmin.entity.po;

import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-13 15:54
 */

@EqualsAndHashCode
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Predicatedefinition {

    private String name;

    private Map<String,String> args=new LinkedHashMap<>();
}
