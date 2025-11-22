/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.controller.web;

import com.vanminh.MyPortfolio.entity.Message;
import com.vanminh.MyPortfolio.entity.User;
import com.vanminh.MyPortfolio.repository.MessageRepository;
import com.vanminh.MyPortfolio.repository.SkillRepository;
import com.vanminh.MyPortfolio.repository.UserRepository;
import com.vanminh.MyPortfolio.service.NotificationService;
import com.vanminh.MyPortfolio.service.ProjectService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author asus
 */
@Controller
public class HomeController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private NotificationService notificationService;

    // --- TRANG CHỦ ---
    @GetMapping("/")
    public String showHomePage(Model model, HttpServletRequest request) {
        // Ép tạo Session để tránh lỗi CSRF với Thymeleaf
        HttpSession session = request.getSession(true);

        User adminProfile = userRepository.findByUsername("admin");
        if (adminProfile != null) {
            model.addAttribute("profile", adminProfile);
        } else {
            model.addAttribute("profile", new User());
        }

        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("skills", skillRepository.findAll());
        return "index";
    }

    // --- TRANG LOGIN ---
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // --- XỬ LÝ GỬI LIÊN HỆ (KHÁCH GỬI) ---
    @PostMapping("/contact/send")
    public String sendMessage(@ModelAttribute Message message) {
        messageRepository.save(message);
        // Gửi email thông báo (Async)
        notificationService.sendEmailNotification(message.getSenderName(), message.getSenderEmail());
        return "redirect:/?sent=true";
    }
}
