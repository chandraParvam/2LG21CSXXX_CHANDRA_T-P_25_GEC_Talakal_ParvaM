package com.example.studentcrud.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentcrud.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>  {
}
