/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vanminh.MyPortfolio.repository;

import com.vanminh.MyPortfolio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author asus
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    //Tìm user theo username (dùng cho chức năng đăng nhập)
    User findByUsername(String username);

}
