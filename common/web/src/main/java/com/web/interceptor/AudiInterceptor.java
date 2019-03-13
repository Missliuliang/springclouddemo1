package com.web.interceptor;


import com.core.entity.po.BasePo;
import com.core.util.UserContextHolder;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Properties;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-13 10:47
 */

@Component
@Intercepts(
        {
                @Signature(type= ParameterHandler.class,method = "setParameters",args = {PreparedStatement.class})
        }
)
public class AudiInterceptor implements Interceptor {

    public static  final  String DEFAUTL_USERNAME="system";


    public Object intercept(Invocation invocation) throws Throwable {
        DefaultParameterHandler parameterHandler= (DefaultParameterHandler) invocation.getTarget();
        if (parameterHandler.getParameterObject() instanceof BasePo){
            BasePo basePo= (BasePo) parameterHandler.getParameterObject();
            String name = StringUtils.defaultIfBlank(UserContextHolder.getInstance().getName(), DEFAUTL_USERNAME);
            basePo.setCreateby(name);
            basePo.setCreateTime(new Date());
            basePo.setUpdateby(name);
            basePo.setUpdateTime(new Date());
        }
        return invocation.proceed();
    }

    public Object plugin(Object o) {
        return Plugin.wrap(o,this);
    }

    public void setProperties(Properties properties) {

    }
}
