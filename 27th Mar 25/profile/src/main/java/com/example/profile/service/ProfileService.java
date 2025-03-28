package com.example.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.profile.model.Profile;
import com.example.profile.repository.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private  ProfileRepository profileRepository;

    public List<Profile> listAll() {
        return profileRepository.findAll();
    }

    public void saveProfile(Profile pro) {
        profileRepository.save(pro);
    }

    public Profile getProfile(Long id) {
        return profileRepository.findById(id).orElse(null);
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }
}
