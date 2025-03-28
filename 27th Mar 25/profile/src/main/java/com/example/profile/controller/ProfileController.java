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

    @GetMapping({"/list", "/edit/{id}"})
public String list(@PathVariable(value = "id", required = false) Long id, Model model) {
    Profile profile;
    if (id != null) {
        profile = profileService.getProfile(id); 
    } else {
        profile = new Profile(); 
    }
    model.addAttribute("profile", profile);
    model.addAttribute("profiles", profileService.listAll()); // Pass all profiles to the view
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
    @PostMapping("/update/{id}")
    public String saveProfile(@PathVariable Long id,@ModelAttribute Profile pro) {
        pro.setId(id);
        profileService.saveProfile(pro);
        return "redirect:/profile/list";
    }

}