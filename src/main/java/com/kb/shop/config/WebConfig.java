package com.kb.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc  // Spring MVC를 활성화
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 정적 리소스 핸들러를 추가
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")  // 정적 리소스의 위치를 설정
                .setCachePeriod(0);  // 캐시 기간을 설정
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 인터셉터를 추가
        registry.addInterceptor(new ThymeleafRequestInterceptor());
    }
}
