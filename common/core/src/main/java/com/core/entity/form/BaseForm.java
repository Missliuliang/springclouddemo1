package com.core.entity.form;

import com.core.entity.po.BasePo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-06 09:44
 */
@ApiModel(value = "baseForm to basePo")
@Data
@Slf4j
public class BaseForm<T extends BasePo> {

    private String userName ;

    public T toPo(Class<T> clazz){
        T t=null ;
        try {
            t=clazz.newInstance();
        }catch (InstantiationException | IllegalAccessException e){
            log.error("PO newInstance Error");
        }
        BeanUtils.copyProperties(this,t);
        return t ;
    }

}
