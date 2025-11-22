/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.controller.admin;

import com.vanminh.MyPortfolio.dto.ProjectDto;
import com.vanminh.MyPortfolio.service.ProjectService;
import com.vanminh.MyPortfolio.util.FileUploadUtil;
import jakarta.validation.Valid;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author asus
 */
@Controller
@RequestMapping("/admin/projects") // Gom nhóm đường dẫn chung
public class ProjectAdminController {

    @Autowired
    private ProjectService projectService;

    // Danh sách dự án
    @GetMapping
    public String showAdminProjectsPage(Model model,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page) {
        Page<ProjectDto> projectPage = projectService.findPaginated(keyword, page, 5);
        model.addAttribute("projectPage", projectPage);
        model.addAttribute("keyword", keyword);
        return "admin/list-projects";
    }

    // Form thêm mới
    @GetMapping("/new")
    public String showNewProjectForm(Model model) {
        model.addAttribute("project", new ProjectDto());
        model.addAttribute("pageTitle", "Thêm dự án mới");
        return "admin/form-project";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String showEditProjectForm(@PathVariable int id, Model model) {
        model.addAttribute("project", projectService.getProjectById(id));
        model.addAttribute("pageTitle", "Cập nhật dự án");
        return "admin/form-project";
    }

    // Xử lý Lưu
    @PostMapping("/save")
    public String saveProject(@Valid @ModelAttribute("project") ProjectDto dto,
            BindingResult bindingResult,
            Model model,
            @RequestParam("imageFile") MultipartFile multipartFile) throws IOException {

        if (bindingResult.hasErrors()) {
            String title = (dto.getId() == null) ? "Thêm dự án mới" : "Cập nhật dự án";
            model.addAttribute("pageTitle", title);
            return "admin/form-project";
        }

        if (!multipartFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            // Lưu ý: Nên đưa đường dẫn này vào file properties thay vì hardcode
            FileUploadUtil.saveFile("D:\\PortfolioData\\", fileName, multipartFile);
            dto.setImageUrl("/uploads/" + fileName);
        }

        projectService.saveProject(dto);
        return "redirect:/admin/projects";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable int id) {
        projectService.deleteProject(id);
        return "redirect:/admin/projects";
    }
}
