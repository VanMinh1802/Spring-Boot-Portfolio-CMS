/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author asus
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
// Bean 1: Trình mã hóa mật khẩu (BẮT BUỘC)

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Luôn dùng BCrypt
    }

    // Bean 2: Bộ lọc bảo mật (Cấu hình chính)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth // Dùng authorizeHttpRequests cho Spring Boot 3
                // CHO PHÉP MỌI NGƯỜI xem trang chủ, form liên hệ, CSS, JS, Images
                .requestMatchers("/", "/home", "/contact", "/css/**", "/js/**", "/images/**", "/uploads/**").permitAll()
                //Cho phép mọi người truy cập API
                .requestMatchers("/api/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                // BẢO VỆ trang Admin
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/actuator/**").hasRole("ADMIN")
                // Tất cả các yêu cầu khác phải đăng nhập
                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                .loginPage("/login") // Trang login tùy chỉnh
                .defaultSuccessUrl("/", true) // Đăng nhập xong thì vào đây
                .permitAll()
                )
                .logout(logout -> logout
                .logoutUrl("/logout") // URL để kích hoạt logout
                .logoutSuccessUrl("/") // Logout -> về trang chủ
                .permitAll()
                )
                .rememberMe(remember -> remember
                .key("uniqueAndSecretKey") // Một chuỗi bí mật bất kỳ
                .tokenValiditySeconds(7 * 24 * 60 * 60) // Thời gian nhớ: 7 ngày (tính bằng giây);
                );
        return http.build();
    }
}
