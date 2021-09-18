package com.william.certificationexammanagement.service.impl;

import com.william.certificationexammanagement.model.EnglishLevel;
import com.william.certificationexammanagement.repository.EnglishLevelRepository;
import com.william.certificationexammanagement.service.EnglishLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnglishLevelServiceImpl implements EnglishLevelService {

    @Autowired
    private EnglishLevelRepository englishLevelRepository;

    @Override
    public List<EnglishLevel> getEnglishLevels() {
        return englishLevelRepository.findAll();
    }

    @Override
    public EnglishLevel getEnglishLevelById(Long id) {
        EnglishLevel englishLevel = null;
        Optional<EnglishLevel> optional = englishLevelRepository.findById(id);

        if (optional.isPresent()) {
            englishLevel = optional.get();
        } else {
            throw new RuntimeException("English Level ID: " + id + " does not exist");
        }

        return null;
    }
}
