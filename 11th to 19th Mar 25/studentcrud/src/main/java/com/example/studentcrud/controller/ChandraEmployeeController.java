package com.example.studentcrud.controller;
import com.example.studentcrud.model.Employee;
import com.example.studentcrud.service.ChandraEmployeeService;
import com.example.studentcrud.dto.EmployeeDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/chandra")
public class ChandraEmployeeController {

    @Autowired
    private ChandraEmployeeService employeeService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("employee",employeeService.listAll() );
        return "employee/index"; 
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/create";
    }
    
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute EmployeeDTO employeeDTO) {
        String uploadDir = new File("src/main/resources/static/upload/").getAbsolutePath();
    
        try {
            // Save profile picture
            MultipartFile profilePic = employeeDTO.getProfilepic();
            String fileName = profilePic.getOriginalFilename();
            File folder = new File(uploadDir);
    
            if (!folder.exists()) {
                folder.mkdirs();
            }
    
            String filePath = uploadDir + File.separator + fileName;
            profilePic.transferTo(new File(filePath));
    
            // Create Employee entity and map fields from DTO
            Employee emp = new Employee();
            emp.setName(employeeDTO.getName());
            emp.setEmail(employeeDTO.getEmail());
            emp.setPhone(employeeDTO.getPhone());
            emp.setProfilepic("/upload/" + fileName);
    
            // Save to the database
            employeeService.saveEmployee(emp);
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return "redirect:/chandra/list";
    }
    



    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/chandra/list";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        model.addAttribute("employee",employeeService.getEmployee(id));
        return "employee/edit";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable Long id,@ModelAttribute Employee emp) {
        emp.setId(id);
        employeeService.saveEmployee(emp);
        return "redirect:/chandra/list";
    }
}