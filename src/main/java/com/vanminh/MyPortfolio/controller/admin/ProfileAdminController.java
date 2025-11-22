/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.controller.admin;

import com.vanminh.MyPortfolio.entity.User;
import com.vanminh.MyPortfolio.repository.UserRepository;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author asus
 */
@Controller
@RequestMapping("/admin")
public class ProfileAdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // --- PROFILE ---
    @GetMapping("/profile")
    public String showProfileForm(Model model) {
        User admin = userRepository.findByUsername("admin");
        model.addAttribute("user", admin);
        model.addAttribute("pageTitle", "Cập nhật Thông tin cá nhân");
        return "admin/profile";
    }

    @PostMapping("/profile/save")
    public String updateProfile(@ModelAttribute("user") User formUser) {
        User existingUser = userRepository.findByUsername("admin");

        // Update fields...
        existingUser.setFullName(formUser.getFullName());
        existingUser.setJobTitle(formUser.getJobTitle());
        existingUser.setBio(formUser.getBio());
        existingUser.setEmail(formUser.getEmail());
        existingUser.setPhoneNumber(formUser.getPhoneNumber());
        existingUser.setAvatarUrl(formUser.getAvatarUrl());
        existingUser.setCvUrl(formUser.getCvUrl());
        existingUser.setGithubUrl(formUser.getGithubUrl());
        existingUser.setLinkedinUrl(formUser.getLinkedinUrl());

        userRepository.save(existingUser);
        return "redirect:/admin/profile?success";
    }

    // --- ĐỔI MẬT KHẨU ---
    @GetMapping("/change-password")
    public String showChangePasswordForm() {
        return "admin/change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model,
            Principal principal) {

        User user = userRepository.findByUsername(principal.getName());

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            model.addAttribute("error", "Mật khẩu hiện tại không đúng!");
            return "admin/change-password";
        }
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu nhập lại không khớp!");
            return "admin/change-password";
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        model.addAttribute("success", "Đổi mật khẩu thành công!");
        return "admin/change-password";
    }
}
