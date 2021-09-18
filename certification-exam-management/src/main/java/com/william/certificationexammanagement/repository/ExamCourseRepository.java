package com.william.certificationexammanagement.repository;

import com.william.certificationexammanagement.model.EnglishLevel;
import com.william.certificationexammanagement.model.ExamCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ExamCourseRepository extends JpaRepository<ExamCourse, Long> {
    Collection<ExamCourse> findByEnglishLevelAndMonthGreaterThanEqualAndYearGreaterThanEqual(EnglishLevel englishLevel, Integer month, Integer year);
}
