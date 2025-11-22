/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "visitor_logs")
public class VisitorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ipAddress; // Địa chỉ IP người dùng
    private String pageVisited; // Trang họ đang xem
    private String userAgent;   // Thông tin trình duyệt (Chrome/Mobile...)
    private LocalDateTime visitTime; // Thời gian vào

    @PrePersist
    protected void onCreate() {
        visitTime = LocalDateTime.now();
    }

    // Constructor & Getter/Setter
    public VisitorLog() {
    }

    public VisitorLog(String ipAddress, String pageVisited, String userAgent) {
        this.ipAddress = ipAddress;
        this.pageVisited = pageVisited;
        this.userAgent = userAgent;
    }

    // --- Getter & Setter (Bạn tự generate bằng Alt+Insert nhé) ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPageVisited() {
        return pageVisited;
    }

    public void setPageVisited(String pageVisited) {
        this.pageVisited = pageVisited;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public LocalDateTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalDateTime visitTime) {
        this.visitTime = visitTime;
    }
}
