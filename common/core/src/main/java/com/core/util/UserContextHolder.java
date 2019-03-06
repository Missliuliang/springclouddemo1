package com.core.util;


import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Optional;

/**
 * @program: springclouddemo
 * @description: 用户上下文
 * @author: liu liang
 * @create: 2019-03-06 09:27
 */
public class UserContextHolder
{

    private ThreadLocal<Map<String,String>> threadLocal;

    private UserContextHolder(){
        this.threadLocal=new ThreadLocal<>();
    }
    /**
     *
     * 创建实例
     */
    public static UserContextHolder getInstance(){
        return SingletonHolder.userHolder ;
    }

    /**
     * 静态内部类单例模式
     * 单例初使化
     */
    public static class  SingletonHolder{
        private static final UserContextHolder userHolder=new UserContextHolder();
    }

    /**
     * 用户上下文中放入信息
     *
     * @param map
     */
    public void setContext(Map<String ,String> map){
        threadLocal.set(map);
    }

    public Map<String ,String> getContext(){
        return threadLocal.get();
    }

    /**
     * 获取上下文中的用户名
     *
     * @return
     */
    public String getName(){
        return Optional.ofNullable(threadLocal.get()).orElse(Maps.newHashMap()).get("user_name");
    }

    public void clear(){
        threadLocal.remove();
    }


}
