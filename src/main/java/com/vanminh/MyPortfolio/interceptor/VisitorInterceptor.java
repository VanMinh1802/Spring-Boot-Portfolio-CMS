/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.interceptor;

import com.vanminh.MyPortfolio.entity.VisitorLog;
import com.vanminh.MyPortfolio.repository.VisitorRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *
 * @author asus
 */
@Component
public class VisitorInterceptor implements HandlerInterceptor {

    @Autowired
    private VisitorRepository visitorRepo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();

        // Chỉ đếm khi vào trang chủ, blog, hoặc xem chi tiết
        // Bỏ qua các file tĩnh (css, js, images) và trang admin
        if (!uri.startsWith("/css") && !uri.startsWith("/js") && !uri.startsWith("/images")
                && !uri.startsWith("/admin") && !uri.startsWith("/api")) {

            String ipAddress = request.getRemoteAddr();
            String userAgent = request.getHeader("User-Agent");

            // Lưu vào DB
            VisitorLog log = new VisitorLog(ipAddress, uri, userAgent);
            visitorRepo.save(log);
        }

        return true; // Cho phép request đi tiếp
    }
}
