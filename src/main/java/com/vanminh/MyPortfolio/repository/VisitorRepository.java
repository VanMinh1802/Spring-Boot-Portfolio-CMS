/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vanminh.MyPortfolio.repository;

import com.vanminh.MyPortfolio.entity.VisitorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asus
 */
@Repository
public interface VisitorRepository extends JpaRepository<VisitorLog, Long> {

    // Đếm tổng số lượt truy cập
    long count();
}
