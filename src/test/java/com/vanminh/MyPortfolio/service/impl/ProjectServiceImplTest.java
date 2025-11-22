/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.service.impl;

import com.vanminh.MyPortfolio.dto.ProjectDto;
import com.vanminh.MyPortfolio.entity.Project;
import com.vanminh.MyPortfolio.exception.ResourceNotFoundException;
import com.vanminh.MyPortfolio.repository.ProjectRepository;
import java.util.Optional;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author asus
 */
@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository; // Giả lập Repository

    @InjectMocks
    private ProjectServiceImpl projectService; // Class chính cần test

    // TEST 1: Kiểm tra xem lưu dự án có thành công không
    @Test
    void saveProject_ShouldReturnProjectDto_WhenSaveIsSuccessful() {
        // 1. Chuẩn bị dữ liệu giả
        ProjectDto inputDto = new ProjectDto();
        inputDto.setTitle("Test Project");
        inputDto.setDescription("Mô tả test");

        Project savedEntity = new Project();
        savedEntity.setId(1);
        savedEntity.setTitle("Test Project");
        savedEntity.setDescription("Mô tả test");

        // 2. Dạy cho Mockito biết: "Nếu ai gọi hàm save, hãy trả về savedEntity"
        when(projectRepository.save(any(Project.class))).thenReturn(savedEntity);

        // 3. Gọi hàm thực tế
        ProjectDto result = projectService.saveProject(inputDto);

        // 4. Kiểm tra kết quả (Assert)
        assertNotNull(result); // Kết quả không được null
        assertEquals(1, result.getId()); // ID phải là 1
        assertEquals("Test Project", result.getTitle()); // Tên phải khớp

        // Kiểm tra xem hàm save của repository có được gọi đúng 1 lần không
        verify(projectRepository, times(1)).save(any(Project.class));
    }

    // TEST 2: Kiểm tra xem lấy ID không tồn tại có báo lỗi không
    @Test
    void getProjectById_ShouldThrowException_WhenProjectNotFound() {
        // 1. Dạy cho Mockito: "Nếu tìm ID 99, hãy trả về Rỗng (Empty)"
        when(projectRepository.findById(99)).thenReturn(Optional.empty());

        // 2. & 3. Kiểm tra xem nó có ném ra lỗi ResourceNotFoundException không
        assertThrows(ResourceNotFoundException.class, () -> {
            projectService.getProjectById(99);
        });
    }
}
