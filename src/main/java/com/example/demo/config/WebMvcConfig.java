package com.example.demo.config;

import com.example.demo.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    /**
     * 设置静态资源映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始进行静态资源映射。。。");
        registry.addResourceHandler("/image/**").addResourceLocations("file:" +"/www/wwwroot/jiaoyou/");
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 注册登录拦截器
     *
     * @author 尘落
     * @date 2023/03/21
     * @email
     **/
    public void addInterceptors(InterceptorRegistry registry){
        //拦截所有请求
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/image/**","/wx/user/login","/register","/fileImage","/payOrder");
    }
}
