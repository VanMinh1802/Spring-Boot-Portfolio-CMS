/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.service;

import com.vanminh.MyPortfolio.entity.BlogPost;
import com.vanminh.MyPortfolio.repository.BlogPostRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author asus
 */
@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Cacheable("posts")
    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAllByOrderByCreatedTimeDesc();
    }

    public BlogPost getPostById(Integer id) {
        return blogPostRepository.findById(id).orElse(null);
    }

    @CacheEvict(value = "posts", allEntries = true)
    public void savePost(BlogPost post) {
        blogPostRepository.save(post);
    }

    @CacheEvict(value = "posts", allEntries = true)
    public void deletePost(Integer id) {
        blogPostRepository.deleteById(id);
    }

    public List<BlogPost> searchPosts(String keyword) {
        if (keyword != null) {
            return blogPostRepository.findByTitleContainingOrSummaryContaining(keyword, keyword);
        }
        return blogPostRepository.findAllByOrderByCreatedTimeDesc();
    }
}
