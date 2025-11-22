/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vanminh.MyPortfolio.repository;

import com.vanminh.MyPortfolio.entity.BlogPost;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asus
 */
@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {

    //Lấy tất cả bài viết, sắp xếp bài mới nhất lên đầu
    List<BlogPost> findAllByOrderByCreatedTimeDesc();

    List<BlogPost> findByTitleContainingOrSummaryContaining(String title, String summary);
}
