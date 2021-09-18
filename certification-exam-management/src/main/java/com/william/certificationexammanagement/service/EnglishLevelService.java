package com.william.certificationexammanagement.service;

import com.william.certificationexammanagement.model.EnglishLevel;

import java.util.List;

public interface EnglishLevelService {
    List<EnglishLevel> getEnglishLevels();
    EnglishLevel getEnglishLevelById(Long id);
}
