package com.william.certificationexammanagement;

import com.william.certificationexammanagement.model.ExamRoom;
import com.william.certificationexammanagement.model.Examine;
import com.william.certificationexammanagement.repository.ExamRoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class ExamRoomRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ExamRoomRepository examRoomRepository;

    @Test
    public void testFindByExamines() {
        Examine examine = entityManager.find(Examine.class, 4L);
        Optional<ExamRoom> optional = examRoomRepository.findByExamines(examine);
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }
    }
}
