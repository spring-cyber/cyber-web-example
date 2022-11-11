package com.oyetalk;
import com.alibaba.fastjson.JSONObject;
import com.cyber.infrastructure.interceptor.JWTTokenInterceptor;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;


@ComponentScan(basePackages = {"com.cyber.*"})
@SpringBootApplication
public class Application {

    @Autowired
    OkHttpClient okHttpClient;

    @Value("${jwt.pathPatterns:/**}")
    List<String> pathPatterns;

    @Value("${jwt.excludePathPatterns:}")
    List<String> excludePathPatterns;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println(JSONObject.toJSONString(pathPatterns));
    }
}
