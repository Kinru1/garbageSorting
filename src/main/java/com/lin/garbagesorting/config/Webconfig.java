//package com.lin.garbagesorting.config;
//
//import com.lin.garbagesorting.Interceptor.JwtInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.annotation.Resource;
//
//@Configuration
//public class Webconfig implements WebMvcConfigurer {
//
//    @Resource
//    JwtInterceptor jwtInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(jwtInterceptor)
//
//                .excludePathPatterns("/login", "/static/**", "/swagger-resources/configuration/ui",
//                        "/swagger-resources",
//                        "/swagger-resources/configuration/security",
//                        "/swagger-ui.html");     //对login页面和静态资源不拦截
//    }
//}

