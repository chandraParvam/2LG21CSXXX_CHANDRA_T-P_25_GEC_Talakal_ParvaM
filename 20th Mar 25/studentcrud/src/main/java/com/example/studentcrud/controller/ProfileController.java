package com.example.studentcrud.controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.studentcrud.model.Profile;
import com.example.studentcrud.service.ProfileService;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import java.io.IOException;
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("profiles",profileService.listAll());
        model.addAttribute("newprofile",new Profile());
        System.out.println("Get Profile List: "+profileService.listAll());
        return "profile/index"; 
    }

    @PostMapping("/save")
    public String saveProfile(@ModelAttribute Profile profile) {
        profileService.saveProfile(profile);
        return "redirect:/profile/list";
    }


    @GetMapping("/delete/{id}")
    public String deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return "redirect:/profile/list";
    }

    @GetMapping("/edit/{id}")
    public String editProfile(@PathVariable Long id, Model model) {
        model.addAttribute("profile",profileService.getProfile(id));
        return "profile/editv2";
    }


}