/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 *
 * @author asus
 */
//Chứa dữ liệu vận chuyển và các quy tắc kiếm tra
public class ProjectDto {

    private Integer id;

    @NotEmpty(message = "Tiêu đề không được để trống")
    @Size(min = 3, message = "Tiêu đề quá ngắn (tối thiểu 3 ký tự)")
    private String title;

    @NotEmpty(message = "Mô tả không được để trống")
    @Size(min = 10, message = "Mô tả quá ngắn (tối thiểu 10 ký tự)")
    private String description;

    @NotEmpty(message = "Vai trò không được để trống")
    private String role;

    private String client;
    private String imageUrl;
    private String projectUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

}
