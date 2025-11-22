/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.controller.admin;

import com.vanminh.MyPortfolio.entity.Skill;
import com.vanminh.MyPortfolio.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author asus
 */
@Controller
@RequestMapping("/admin/skills")
public class SkillAdminController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping
    public String showSkillsPage(Model model) {
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute("newSkill", new Skill());
        return "admin/list-skills";
    }

    @PostMapping("/save")
    public String saveSkill(@ModelAttribute("newSkill") Skill skill) {
        skillRepository.save(skill);
        return "redirect:/admin/skills";
    }

    @GetMapping("/delete/{id}")
    public String deleteSkill(@PathVariable Integer id) {
        skillRepository.deleteById(id);
        return "redirect:/admin/skills";
    }
}
