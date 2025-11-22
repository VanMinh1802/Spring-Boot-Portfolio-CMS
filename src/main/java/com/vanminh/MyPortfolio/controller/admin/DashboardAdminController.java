/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.controller.admin;

import com.vanminh.MyPortfolio.MyPortfolioApplication;
import com.vanminh.MyPortfolio.repository.BlogPostRepository;
import com.vanminh.MyPortfolio.repository.MessageRepository;
import com.vanminh.MyPortfolio.repository.ProjectRepository;
import com.vanminh.MyPortfolio.repository.SkillRepository;
import com.vanminh.MyPortfolio.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author asus
 */
@Controller
public class DashboardAdminController {

    @Autowired
    private ProjectRepository projectRepo;
    @Autowired
    private BlogPostRepository blogRepo;
    @Autowired
    private MessageRepository messageRepo;
    @Autowired
    private SkillRepository skillRepo;
    @Autowired
    private VisitorRepository visitorRepo; // Tiêm thêm Repo này

    @GetMapping("/admin/dashboard")
    public String showDashboard(Model model) {
        // Lấy số lượng các thực thể
        long countProjects = projectRepo.count();
        long countBlogs = blogRepo.count();
        long countMessages = messageRepo.count();
        long countSkills = skillRepo.count();
        // Thêm đếm lượt truy cập
        long countVisits = visitorRepo.count();
        // Tính Uptime
        long uptimeMillis = System.currentTimeMillis() - MyPortfolioApplication.START_TIME;
        long uptimeSeconds = uptimeMillis / 1000;
        long hours = uptimeSeconds / 3600;
        long minutes = (uptimeSeconds % 3600) / 60;
        String uptimeStr = hours + " giờ " + minutes + " phút";

        // Gửi ra giao diện
        model.addAttribute("countProjects", countProjects);
        model.addAttribute("countBlogs", countBlogs);
        model.addAttribute("countMessages", countMessages);
        model.addAttribute("countSkills", countSkills);
        model.addAttribute("countVisits", countVisits);
        model.addAttribute("uptime", uptimeStr);

        return "admin/dashboard";
    }
}
