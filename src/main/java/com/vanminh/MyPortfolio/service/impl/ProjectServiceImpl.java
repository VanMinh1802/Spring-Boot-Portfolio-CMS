/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.service.impl;

import com.vanminh.MyPortfolio.service.ProjectService;
import com.vanminh.MyPortfolio.dto.ProjectDto;
import com.vanminh.MyPortfolio.entity.Project;
import com.vanminh.MyPortfolio.exception.ResourceNotFoundException;
import com.vanminh.MyPortfolio.repository.ProjectRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author asus
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    // Khởi tạo Logger
    private static final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    private ProjectRepository projectRepository;

    // --- Hàm chuyển đổi Entity -> DTO ---
    private ProjectDto convertToDto(Project entity) {
        ProjectDto dto = new ProjectDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setRole(entity.getRole());
        dto.setClient(entity.getClient());
        dto.setImageUrl(entity.getImageUrl());
        dto.setProjectUrl(entity.getProjectUrl());
        return dto;
    }

    // --- Hàm chuyển đổi DTO -> Entity ---
    private void mapDtoToEntity(ProjectDto dto, Project entity) {
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setRole(dto.getRole());
        entity.setClient(dto.getClient());
        entity.setImageUrl(dto.getImageUrl());
        entity.setProjectUrl(dto.getProjectUrl());
    }

    @Override
    @Cacheable("projects")//Khi lấy danh sách, lưu vào cache tên là "projects"
    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProjectDto> findPaginated(String keyword, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Project> entityPage;

        if (keyword != null && !keyword.isEmpty()) {
            //Tìm kiếm
            entityPage = projectRepository.findByTitleContainingOrRoleContaining(keyword, keyword, pageable);
        } else {
            //Lấy tất cả phân trang
            entityPage = projectRepository.findAll(pageable);
        }
        return entityPage.map(this::convertToDto);
    }

    @Override
    public ProjectDto getProjectById(int id) {
        Project entity = projectRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Không tìm thấy Project với ID: {}", id);
                    return new ResourceNotFoundException("Project not found with id: " + id);
                });
        return convertToDto(entity);
    }

    @Override
    @CacheEvict(value = "projects", allEntries = true)//Khi Thêm/Sửa/Xóa, phải XÓA cache cũ đi để nó cập nhật cái mới
    public ProjectDto saveProject(ProjectDto dto) {
        Project entity;
        if (dto.getId() != null) {
            // LOGGING: Ghi nhật ký khi cập nhật
            log.info("Đang cập nhật dự án ID: {}", dto.getId());
            entity = projectRepository.findById(dto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        } else {
            // LOGGING: Ghi nhật ký khi thêm mới
            log.info("Đang tạo dự án mới: {}", dto.getTitle());
            entity = new Project();
        }

        mapDtoToEntity(dto, entity);
        Project saved = projectRepository.save(entity);

        log.info("Đã lưu thành công dự án ID: {}", saved.getId());
        return convertToDto(saved);
    }

    @Override
    @CacheEvict(value = "projects", allEntries = true)
    public void deleteProject(int id) {
        if (!projectRepository.existsById(id)) {
            log.error("Cố gắng xóa dự án không tồn tại ID: {}", id);
            throw new ResourceNotFoundException("Project not found");
        }
        projectRepository.deleteById(id);
        log.info("Đã xóa dự án ID: {}", id);
    }

}
