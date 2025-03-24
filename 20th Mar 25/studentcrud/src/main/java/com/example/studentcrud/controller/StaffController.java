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
import com.example.studentcrud.model.Staff;
import com.example.studentcrud.service.StaffService;
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
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("staff",staffService.listAll() );
        return "staff/index"; 
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("staff", new Staff());
        return "staff/createv2";
    }

    @PostMapping("/save")
    public String saveStaff(
    @RequestParam("name") String name,
    @RequestParam("email") String email,
    @RequestParam("phone") String phone,@RequestParam("profilepic") MultipartFile profilePic) {
        String uploadDir = new File("src/main/resources/static/upload/").getAbsolutePath();
        try {
        String fileName = profilePic.getOriginalFilename();
        // String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        // String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        // String fileName = "image_" + timeStamp + extension; 

        String filePath = uploadDir + File.separator + fileName;
        profilePic.transferTo(new File(filePath));
        Staff stu = new Staff();
        stu.setName(name);
        stu.setEmail(email);
        stu.setPhone(phone);
        stu.setProfilepic("/upload/" + fileName);
        staffService.saveStaff(stu);
    } catch (IOException e) {
        e.printStackTrace();
    }
        return "redirect:/staff/list";
    }


    @PostMapping("/savev2")
    public String saveStaffv2(
    @RequestParam("name") String name,
    @RequestParam("email") String email,
    @RequestParam("phone") String phone,@RequestParam("profilepic") MultipartFile profilePic) {
        String uploadDir = new File("src/main/resources/static/upload/").getAbsolutePath();
        try {
        String originalFileName = profilePic.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = "image_" + timeStamp + extension; 

        Path filePath = Paths.get(uploadDir + File.separator + fileName);
        Files.copy(profilePic.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        Staff stu = new Staff();
        stu.setName(name);
        stu.setEmail(email);
        stu.setPhone(phone);
        stu.setProfilepic("/upload/" + fileName);
        staffService.saveStaff(stu);
    } catch (IOException e) {
        e.printStackTrace();
    }
        return "redirect:/staff/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return "redirect:/staff/list";
    }

    @GetMapping("/edit/{id}")
    public String editStaff(@PathVariable Long id, Model model) {
        model.addAttribute("staff",staffService.getStaff(id));
        return "staff/editv2";
    }

    @PostMapping("/update/{id}")
    public String updateStaff(@PathVariable Long id,
    @RequestParam("name") String name,
    @RequestParam("email") String email,
    @RequestParam("phone") String phone,@RequestParam("profilepic") MultipartFile profilePic) {
        
        String uploadDir = new File("src/main/resources/static/upload/").getAbsolutePath();
        try {
        String originalFileName = profilePic.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = "image_" + timeStamp + extension;
        String filePath = uploadDir + File.separator + fileName;
        profilePic.transferTo(new File(filePath));
        Staff stu = new Staff();
        stu.setId(id);
        stu.setName(name);
        stu.setEmail(email);
        stu.setPhone(phone);
        stu.setProfilepic("/upload/" + fileName);
        staffService.saveStaff(stu);
    } catch (IOException e) {
        e.printStackTrace();
    }
        return "redirect:/staff/list";
    }

    @PostMapping("/updatev2/{id}")
    public String updateStaffv2(@PathVariable Long id,
    @RequestParam("name") String name,
    @RequestParam("email") String email,
    @RequestParam("phone") String phone,@RequestParam("profilepic") MultipartFile profilePic) {
        
        String uploadDir = new File("src/main/resources/static/upload/").getAbsolutePath();
        try {
        String originalFileName = profilePic.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = "image_" + timeStamp + extension;
        Path filePath = Paths.get(uploadDir + File.separator + fileName);
        Files.copy(profilePic.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        Staff stu = new Staff();
        stu.setId(id);
        stu.setName(name);
        stu.setEmail(email);
        stu.setPhone(phone);
        stu.setProfilepic("/upload/" + fileName);
        staffService.saveStaff(stu);
    } catch (IOException e) {
        e.printStackTrace();
    }
        return "redirect:/staff/list";
    }
}