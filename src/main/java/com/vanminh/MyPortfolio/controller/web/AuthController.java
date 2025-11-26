/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.controller.web;

import com.vanminh.MyPortfolio.entity.Role;
import com.vanminh.MyPortfolio.entity.User;
import com.vanminh.MyPortfolio.repository.RoleRepository;
import com.vanminh.MyPortfolio.repository.UserRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // 1. Hiển thị Form Đăng ký
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // Trả về file register.html
    }

    // 2. Xử lý Đăng ký
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        // Kiểm tra trùng Username
        if (userRepository.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại!");
            return "register";
        }

        // Mã hóa mật khẩu
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Mặc định gán quyền USER (Khách)
        Role userRole = roleRepository.findByName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        // Set các thông tin mặc định khác (để tránh null)
        user.setFullName(user.getUsername()); // Tạm lấy username làm tên

        // Lưu vào DB
        userRepository.save(user);

        return "redirect:/login?register_success";
    }
}
