package com.william.certificationexammanagement.repository;

import com.william.certificationexammanagement.model.Exam;
import com.william.certificationexammanagement.model.ExamRoom;
import com.william.certificationexammanagement.model.Examine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    Collection<Exam> findByExamine(Examine examine);
    Optional<Exam> findByExamineAndExamRoom(Examine examine, ExamRoom examRoom);
}
