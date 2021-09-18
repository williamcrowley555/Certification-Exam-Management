package com.william.certificationexammanagement.repository;

import com.william.certificationexammanagement.model.EnglishLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnglishLevelRepository  extends JpaRepository<EnglishLevel, Long> {
}
