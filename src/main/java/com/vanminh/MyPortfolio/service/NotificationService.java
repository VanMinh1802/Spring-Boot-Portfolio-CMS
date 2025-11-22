/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author asus
 */
@Service
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    @Async // <--- QUAN TRỌNG: Đánh dấu hàm này chạy ở luồng riêng (Background)
    public void sendEmailNotification(String name, String email) {
        log.info("--> BẮT ĐẦU gửi email cho: " + name);

        try {
            // Giả vờ ngủ 5 giây (mô phỏng mạng lag hoặc gửi mail tốn thời gian)
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("--> ĐÃ GỬI XONG email cho: " + email);
    }
}
