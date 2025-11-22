/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.config;

import com.vanminh.MyPortfolio.interceptor.VisitorInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author asus
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private VisitorInterceptor visitorInterceptor; // Tiêm Interceptor vào

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Đường dẫn ảo /uploads/ sẽ trỏ về thư mục thật D:/PortfolioData/
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:///D:\\JavaBackend\\Java_Spring_Boot\\MyPortfolio\\PortfolioData/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Kích hoạt Interceptor cho mọi đường dẫn
        registry.addInterceptor(visitorInterceptor);
    }
}
