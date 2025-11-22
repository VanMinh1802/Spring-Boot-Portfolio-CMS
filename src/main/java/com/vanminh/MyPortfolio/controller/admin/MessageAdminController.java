/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vanminh.MyPortfolio.controller.admin;

import com.vanminh.MyPortfolio.entity.Message;
import com.vanminh.MyPortfolio.repository.MessageRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author asus
 */
@Controller
@RequestMapping("/admin/messages")
public class MessageAdminController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public String showMessages(Model model) {
        List<Message> messages = messageRepository.findAllByOrderBySentTimeDesc();
        model.addAttribute("messages", messages);
        return "admin/list-messages";
    }

    @GetMapping("/delete/{id}")
    public String deleteMessage(@PathVariable int id) {
        messageRepository.deleteById(id);
        return "redirect:/admin/messages";
    }
}
