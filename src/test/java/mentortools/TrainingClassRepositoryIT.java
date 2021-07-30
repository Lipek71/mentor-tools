package mentortools;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
class TrainingClassRepositoryIT {

    @Autowired
    TrainingClassRepository repository;

    @Test
    @Transactional
    void setTrainingClassTest() {
        TrainingClass trainingClass = new TrainingClass(1L,
                "Beginner Java programmer",
                LocalDate.of(2021, 5, 1),
                LocalDate.of(2021, 8, 1));

       repository.save(trainingClass);

       List<TrainingClass> trainingClassList = repository.findAll();

       assertEquals(1,trainingClassList.size());
       assertEquals("Beginner Java programmer", trainingClassList.get(0).getTitle());

       repository.setTrainingClass(1L,
               "Beginner C++ Programmer",
               LocalDate.of(2021, 5, 1),
               LocalDate.of(2021, 8, 1));

        List<TrainingClass> trainingClassListUpdate = repository.findAll();
        assertEquals("Beginner C++ programmer", trainingClassList.get(0).getTitle());

    }
}