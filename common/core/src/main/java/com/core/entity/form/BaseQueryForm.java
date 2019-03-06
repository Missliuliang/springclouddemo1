package com.core.entity.form;

import com.core.entity.po.BasePo;
import io.swagger.annotations.ApiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-06 09:51
 */
@ApiModel
@Slf4j
public class BaseQueryForm<P extends BasePo > extends BaseForm {

    public P toParam(Class<P> clazz){
        P p=null;
        try {
            p=clazz.newInstance();
        }catch (IllegalAccessException | InstantiationException e){
            log.error(" param newInstance Error");
        }
        BeanUtils.copyProperties(this,p);
        return p ;
    }
}
