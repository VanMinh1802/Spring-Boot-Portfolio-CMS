/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vanminh.MyPortfolio.repository;

import com.vanminh.MyPortfolio.entity.Message;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asus
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    // Sắp xếp tin nhắn mới nhất lên đầu
    List<Message> findAllByOrderBySentTimeDesc();
}
