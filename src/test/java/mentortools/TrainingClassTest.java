package mentortools;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TrainingClassTest {


    TrainingClass trainingClass = new TrainingClass(1L,
            "Beginner Java programmer",
            LocalDate.of(2021, 5, 1),
            LocalDate.of(2021, 8, 1));


    @Test
    void gettersGettersTest() {
        assertEquals(1L, trainingClass.getId());
        assertEquals("Beginner Java programmer", trainingClass.getTitle());
        assertEquals(LocalDate.of(2021, 5, 1), trainingClass.getStartDate());
        assertEquals(LocalDate.of(2021, 8, 1), trainingClass.getEndDate());

        trainingClass.setId(2L);
        trainingClass.setTitle("Beginner C++ programmer");
        trainingClass.setStartDate(LocalDate.of(2022, 4, 1));
        trainingClass.setEndDate(LocalDate.of(2022, 6, 1));

        assertEquals(2L, trainingClass.getId());
        assertEquals("Beginner C++ programmer", trainingClass.getTitle());
        assertEquals(LocalDate.of(2022, 4, 1), trainingClass.getStartDate());
        assertEquals(LocalDate.of(2022, 6, 1), trainingClass.getEndDate());

    }

    @Test
    void testEquals() {
        assertTrue(trainingClass.getTitle().equals("Beginner Java programmer"));
    }

    @Test
    void testToString() {
        assertEquals("TrainingClass(id=1, title=Beginner Java programmer, startDate=2021-05-01, endDate=2021-08-01)", trainingClass.toString());
    }
}