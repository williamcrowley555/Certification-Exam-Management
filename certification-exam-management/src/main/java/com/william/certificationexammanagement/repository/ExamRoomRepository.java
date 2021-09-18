package com.william.certificationexammanagement.repository;

import com.william.certificationexammanagement.model.ExamRoom;
import com.william.certificationexammanagement.model.Examine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRoomRepository  extends JpaRepository<ExamRoom, Long> {

    Optional<ExamRoom> findByExamines(Examine examine);
}
