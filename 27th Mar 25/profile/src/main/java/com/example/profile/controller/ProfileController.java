package com.example.profile.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.profile.model.Profile;
import com.example.profile.service.ProfileService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("profile", new Profile());
        model.addAttribute("profiles",profileService.listAll() );
        return "profile/index"; 
    }


    @PostMapping("/save")
    public String saveProfile(@ModelAttribute Profile pro) {
        profileService.saveProfile(pro);
        return "redirect:/profile/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return "redirect:/profile/list";
    }

}