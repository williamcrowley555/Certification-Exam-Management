package com.william.certificationexammanagement.repository;

import com.william.certificationexammanagement.model.ExamCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamCourseRepository extends JpaRepository<ExamCourse, Long> {
}
