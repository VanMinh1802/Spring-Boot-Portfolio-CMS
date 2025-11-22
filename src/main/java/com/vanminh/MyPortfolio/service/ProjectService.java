/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vanminh.MyPortfolio.service;

import com.vanminh.MyPortfolio.dto.ProjectDto;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author asus
 */
public interface ProjectService {
// Lấy tất cả (cho trang chủ)

    List<ProjectDto> getAllProjects();

    // Lấy có phân trang & tìm kiếm (cho trang admin)
    Page<ProjectDto> findPaginated(String keyword, int pageNo, int pageSize);

    // Lấy 1 dự án theo ID
    ProjectDto getProjectById(int id);

    // Lưu (Thêm mới hoặc Cập nhật)
    ProjectDto saveProject(ProjectDto projectDto);

    // Xóa
    void deleteProject(int id);
}
