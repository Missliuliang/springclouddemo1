package com.authenticationclient.provider;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import com.core.entity.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-15 15:58
 */
@Component
@FeignClient(name = "authentication-server" ,fallback = AuthProvider.AuthProviderFallback.class)
public interface AuthProvider {

    @PostMapping(value = "/auth/permission")
    Result auth(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
            @RequestParam("url") String url ,@RequestParam("method") String method
    );

    @Component
    class AuthProviderFallback implements AuthProvider{
        /**
         * 降级统一返回无权限
         *
         * @param authorization
         * @param url
         * @param method
         * @return <pre>
         * Result:
         * {
         *   code:"-1"
         *   mesg:"系统异常"
         * }
         * </pre>
         */
        @Override
        public Result auth(String authorization, String url, String method) {
            return Result.fail();
        }
    }
}
