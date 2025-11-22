/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.controller.admin;

import com.vanminh.MyPortfolio.entity.Comment;
import com.vanminh.MyPortfolio.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author asus
 */
@Controller
public class CommentAdminController {

    @Autowired
    private CommentRepository commentRepo;

    // Xem danh sách tất cả bình luận
    @GetMapping("/admin/comments")
    public String listComments(Model model) {
        model.addAttribute("comments", commentRepo.findAllByOrderByCreatedDateDesc());
        return "admin/list-comments";
    }

    // Duyệt bình luận
    @GetMapping("/admin/comments/approve/{id}")
    public String approveComment(@PathVariable Integer id) {
        Comment c = commentRepo.findById(id).orElse(null);
        if (c != null) {
            c.setApproved(true);
            commentRepo.save(c);
        }
        return "redirect:/admin/comments";
    }

    // Xóa bình luận
    @GetMapping("/admin/comments/delete/{id}")
    public String deleteComment(@PathVariable Integer id) {
        commentRepo.deleteById(id);
        return "redirect:/admin/comments";
    }
}
