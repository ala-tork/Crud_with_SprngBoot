package com.mycompany.Controllers;

import com.mycompany.Entitys.UserEntity;
import com.mycompany.Expectations.UserNotFoundExpectation;
import com.mycompany.Services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    private UserServices service;
    @GetMapping("/users")
    public String ShowUserList(Model model){
        List<UserEntity> Users= service.listAll();
        model.addAttribute("listUsers",Users);
        return "users";
    }
    @GetMapping("/users/new")
    public String ShowForm(Model model){
        model.addAttribute("user",new UserEntity());
        model.addAttribute("pagetitle","Add New User");
        return "user_form";
    }
    @PostMapping("/user/save")
    public String saveUser(UserEntity user ,RedirectAttributes ra){
        service.save(user);
        ra.addFlashAttribute("message","the user has been saved successfuly.");
        return "redirect:/users";
    }
    @GetMapping("user/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id,Model model,RedirectAttributes ra){
        try{
            UserEntity user =service.get(id);
            model.addAttribute("user",user);
            model.addAttribute("pagetitle","Edit user (ID : "+ id +")");
            return "user_form";
        }catch (UserNotFoundExpectation e){
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/users";
        }
    }
    @GetMapping("user/delete/{id}")
    public String Showdelete(@PathVariable("id") Integer id,Model model,RedirectAttributes ra){
        try{
            service.deletebyId(id);
            ra.addFlashAttribute("message","user delete successfuly");
        }catch (UserNotFoundExpectation e){
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/users";
    }
}
