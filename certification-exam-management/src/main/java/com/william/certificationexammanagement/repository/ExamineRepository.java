package com.william.certificationexammanagement.repository;

import com.william.certificationexammanagement.model.Examine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ExamineRepository  extends JpaRepository<Examine, Long> {

    Optional<Examine> findByPhone(String phone);
    Optional<Examine> findByExamineId(String examineId);

    @Query("SELECT e FROM Examine e WHERE CONCAT(e.firstName, e.lastName) LIKE CONCAT('%', :fullName, '%')")
    Collection<Examine> findByFullName(String fullName);

    @Query("SELECT e FROM Examine e WHERE CONCAT(e.firstName, e.lastName) LIKE CONCAT('%', :fullName, '%') AND e.phone = :phone")
    Optional<Examine> findByFullNameAndPhone(String fullName, String phone);
}
