/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.scheduler;

import com.vanminh.MyPortfolio.repository.VisitorRepository;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author asus
 */
@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private VisitorRepository visitorRepo;

    // Cấu hình chạy mỗi 60 giây (60000 ms)
    @Scheduled(fixedRate = 60000)
    public void reportCurrentVisitorCount() {
        long count = visitorRepo.count();
        log.info("=== BÁO CÁO ĐỊNH KỲ (" + LocalDateTime.now() + ") ===");
        log.info("Tổng số lượt truy cập tính đến hiện tại: " + count);
        log.info("Server vẫn đang hoạt động ổn định.");
        log.info("==============================================");
    }
}
