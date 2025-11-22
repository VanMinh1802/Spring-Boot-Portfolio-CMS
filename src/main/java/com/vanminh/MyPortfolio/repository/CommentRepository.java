/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vanminh.MyPortfolio.repository;

import com.vanminh.MyPortfolio.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asus
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    // 1. Lấy bình luận của một bài viết cụ thể (Chỉ lấy cái đã Duyệt) -> Cho khách xem
    List<Comment> findByPostIdAndIsApprovedTrueOrderByCreatedDateDesc(Integer postId);

    // 2. Lấy tất cả bình luận (Sắp xếp mới nhất) -> Cho Admin quản lý
    List<Comment> findAllByOrderByCreatedDateDesc();
}
