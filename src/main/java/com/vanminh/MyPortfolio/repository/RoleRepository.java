/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vanminh.MyPortfolio.repository;

import com.vanminh.MyPortfolio.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author asus
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    //Tìm quyền admin
    Role findByName(String name);
}
