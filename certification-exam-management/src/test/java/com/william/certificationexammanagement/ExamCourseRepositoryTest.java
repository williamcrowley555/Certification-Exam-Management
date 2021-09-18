package com.william.certificationexammanagement;

import com.william.certificationexammanagement.model.EnglishLevel;
import com.william.certificationexammanagement.model.ExamCourse;
import com.william.certificationexammanagement.model.ExamRoom;
import com.william.certificationexammanagement.model.Examine;
import com.william.certificationexammanagement.repository.ExamCourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class ExamCourseRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ExamCourseRepository examCourseRepository;

    @Test
    public void testFindByEnglishLevelAndMonthGreaterThanEqualAndYearGreaterThanEqual() {
        EnglishLevel englishLevel = entityManager.find(EnglishLevel.class, 1L);
        List<ExamCourse> examCourses = (List<ExamCourse>) examCourseRepository.findByEnglishLevelAndMonthGreaterThanEqualAndYearGreaterThanEqual(englishLevel, 10, 2021);
        examCourses.forEach(System.out::println);
    }
}
