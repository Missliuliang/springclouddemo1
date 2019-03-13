package com.web.interceptor;

import com.core.util.UserContextHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-13 10:59
 */
@Component
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    public static  final  String X_CLIENT_TOKEN_USER="x_client_token_user";

    public static  final  String X_CLIENT_TOKEN ="x_client_token";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        checkToken(request.getHeader(X_CLIENT_TOKEN));
        String name = StringUtils.defaultIfBlank(request.getHeader(X_CLIENT_TOKEN_USER), "{}");
        UserContextHolder.getInstance().setContext(new ObjectMapper().readValue(name,Map.class));
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserContextHolder.getInstance().clear();
    }

    private void checkToken(String token){
        log.debug("//TODO 校验Token:{}" ,token);
    }
}
