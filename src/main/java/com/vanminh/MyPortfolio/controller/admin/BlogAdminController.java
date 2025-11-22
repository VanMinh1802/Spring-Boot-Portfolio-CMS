/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.controller.admin;

import com.vanminh.MyPortfolio.entity.BlogPost;
import com.vanminh.MyPortfolio.entity.Comment;
import com.vanminh.MyPortfolio.repository.CommentRepository;
import com.vanminh.MyPortfolio.service.BlogPostService;
import com.vanminh.MyPortfolio.util.FileUploadUtil;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author asus
 */
@Controller
public class BlogAdminController {

    @Autowired
    private BlogPostService blogService;

    @Autowired
    private CommentRepository commentRepository;

    // --- PUBLIC: KHÁCH XEM ---
    // 1. Trang danh sách Blog
    @GetMapping("/blog")
    public String viewBlogList(Model model, @RequestParam(required = false) String keyword) {
        List<BlogPost> list;
        if (keyword != null && !keyword.isEmpty()) {
            list = blogService.searchPosts(keyword);
        } else {
            list = blogService.getAllPosts();
        }

        model.addAttribute("posts", list);
        model.addAttribute("keyword", keyword); // Gửi lại keyword để hiện trong ô input
        return "blog";
    }

    // 2. Trang chi tiết bài viết
    @GetMapping("/blog/{id}")
    public String viewBlogDetail(@PathVariable Integer id, Model model) {
        BlogPost post = blogService.getPostById(id);
        if (post == null) {
            return "redirect:/blog";
        }

        model.addAttribute("post", post);

        // Lấy danh sách bình luận ĐÃ DUYỆT của bài này
        model.addAttribute("comments", commentRepository.findByPostIdAndIsApprovedTrueOrderByCreatedDateDesc(id));

        // Gửi một object Comment rỗng để làm form
        model.addAttribute("newComment", new com.vanminh.MyPortfolio.entity.Comment());

        return "blog-detail";
    }

    // --- ADMIN: QUẢN LÝ ---
    // 3. Trang quản lý danh sách (Admin)
    @GetMapping("/admin/blogs")
    public String showAdminBlogs(Model model) {
        model.addAttribute("posts", blogService.getAllPosts());
        return "admin/list-posts";
    }

    // 4. Form thêm mới
    @GetMapping("/admin/blogs/new")
    public String showNewPostForm(Model model) {
        model.addAttribute("post", new BlogPost());
        return "admin/form-post";
    }

    // 5. Form sửa
    @GetMapping("/admin/blogs/edit/{id}")
    public String showEditPostForm(@PathVariable Integer id, Model model) {
        model.addAttribute("post", blogService.getPostById(id));
        return "admin/form-post";
    }

    // 6. Lưu bài viết (Có upload ảnh)
    @PostMapping("/admin/blogs/save")
    public String savePost(@ModelAttribute BlogPost post,
            @RequestParam("imageFile") MultipartFile multipartFile) throws IOException {

        // Xử lý upload ảnh (tương tự Project)
        if (!multipartFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            FileUploadUtil.saveFile("D:\\JavaBackend\\Java_Spring_Boot\\MyPortfolio\\PortfolioData/", fileName, multipartFile);
            post.setImageUrl("/uploads/" + fileName);
        } else {
            // Nếu đang sửa mà không chọn ảnh mới -> Lấy ảnh cũ từ DB (Logic đơn giản)
            if (post.getId() != null) {
                BlogPost oldPost = blogService.getPostById(post.getId());
                if (oldPost != null && post.getImageUrl() == null) {
                    post.setImageUrl(oldPost.getImageUrl());
                    post.setCreatedTime(oldPost.getCreatedTime()); // Giữ nguyên ngày tạo
                }
            }
        }

        blogService.savePost(post);
        return "redirect:/admin/blogs";
    }

    // 7. Xóa
    @GetMapping("/admin/blogs/delete/{id}")
    public String deletePost(@PathVariable Integer id) {
        blogService.deletePost(id);
        return "redirect:/admin/blogs";
    }

    //--- (Xử lý gửi bình luận) ---
    @PostMapping("/blog/{id}/comment")
    public String addComment(@PathVariable Integer id,
            @ModelAttribute("newComment") Comment comment) {

        // Cách 1: Dùng entityManager.getReference (Hiệu năng cao hơn, tránh select thừa)
        // Hoặc Cách 2 đơn giản: Lấy post bình thường nhưng không cascade update
        BlogPost post = blogService.getPostById(id);

        if (post != null) {
            // TẠO MỚI HOÀN TOÀN một đối tượng Comment để tránh dính líu ID cũ (nếu có)
            Comment commentToSave = new Comment();
            commentToSave.setName(comment.getName());
            commentToSave.setEmail(comment.getEmail());
            commentToSave.setContent(comment.getContent());
            commentToSave.setApproved(false);
            commentToSave.setPost(post); // Gán bài viết

            commentRepository.save(commentToSave);
        }
        return "redirect:/blog/" + id + "?comment_success";
    }
}
