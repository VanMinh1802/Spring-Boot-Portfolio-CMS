/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vanminh.MyPortfolio.repository;

import com.vanminh.MyPortfolio.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author asus
 */
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    //Tìm kiếm dự án
    //pageable: hỗ trợ phân trang
    Page<Project> findByTitleContainingOrRoleContaining(String title, String role, Pageable pageable);

}
