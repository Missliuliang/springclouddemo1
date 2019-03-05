package com.authorizationserver.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Resource {

    private String code ;
    private String name ;
    private String url ;
    private String type ;
    private String method ;
    private String description ;




}
