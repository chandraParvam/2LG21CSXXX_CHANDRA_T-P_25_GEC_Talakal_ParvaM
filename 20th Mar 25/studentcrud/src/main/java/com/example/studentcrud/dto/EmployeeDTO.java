package com.example.studentcrud.dto;

import org.springframework.web.multipart.MultipartFile;

public class EmployeeDTO {
    private String name;
    private String email;
    private String phone;
    private MultipartFile profilepic;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MultipartFile getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(MultipartFile profilepic) {
        this.profilepic = profilepic;
    }
}
