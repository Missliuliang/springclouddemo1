package com.core.entity.po;



import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
public class  BasePo implements Serializable {

    public static final String DEFAULT_USERNAME ="system";

    private Long id=1L;

    private String createby=DEFAULT_USERNAME;
    private String updateby=DEFAULT_USERNAME;

    private Date createTime=Date.from(ZonedDateTime.now().toInstant());
    private Date updateTime=Date.from(ZonedDateTime.now().toInstant());

}
