package com.william.certificationexammanagement.repository;

import com.william.certificationexammanagement.model.ExamCourse;
import com.william.certificationexammanagement.model.ExamRoom;
import com.william.certificationexammanagement.model.Examine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ExamRoomRepository  extends JpaRepository<ExamRoom, Long> {

    Collection<ExamRoom> findByExamines(Examine examine);
    Collection<ExamRoom> findExamRoomByExamCourse(ExamCourse examCourse);
}
