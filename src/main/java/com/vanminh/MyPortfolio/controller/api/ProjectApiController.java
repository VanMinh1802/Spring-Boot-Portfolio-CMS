/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.controller.api;

import com.vanminh.MyPortfolio.dto.ProjectDto;
import com.vanminh.MyPortfolio.service.ProjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asus
 */
@RestController // Đây là RestController, chuyên trả về JSON
@RequestMapping("/api/v1/projects") // Đường dẫn chung cho API này
@CrossOrigin("*") // Cho phép các trang web khác (React, Vue...) gọi vào API này
public class ProjectApiController {

    @Autowired
    private ProjectService projectService;

    // API 1: Lấy danh sách tất cả dự án
    // URL: GET http://localhost:8081/api/v1/projects
    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects); // Trả về mã 200 OK và danh sách JSON
    }

    // API 2: Lấy chi tiết 1 dự án theo ID
    // URL: GET http://localhost:8081/api/v1/projects/1
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable int id) {
        try {
            ProjectDto project = projectService.getProjectById(id);
            return ResponseEntity.ok(project);
        } catch (Exception e) {
            // Nếu không tìm thấy thì trả về lỗi 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}
