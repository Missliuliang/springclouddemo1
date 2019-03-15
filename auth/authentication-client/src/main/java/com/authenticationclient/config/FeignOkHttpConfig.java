package com.authenticationclient.config;

import feign.Feign;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-15 15:47
 */
@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class FeignOkHttpConfig  {


    private int feignOkHttpTimeOut=60;
    private int feignConnectionTimeOut=60;
    private int feignWriterTimeOut=120;

    @Bean
    public okhttp3.OkHttpClient okHttpClient(){
        return new okhttp3.OkHttpClient.Builder()
                .readTimeout(feignOkHttpTimeOut, TimeUnit.SECONDS)
                .connectTimeout(feignConnectionTimeOut,TimeUnit.SECONDS)
                .writeTimeout(feignWriterTimeOut,TimeUnit.SECONDS)
 //               .connectionPool(new ConnectionPool(int maxIdleConnections, long keepAliveDuration, TimeUnit timeUnit))   //自定义链接池
//				.addInterceptor(XXXXXXXInterceptor) 	//自定义拦截器
                .build();
    }
}
