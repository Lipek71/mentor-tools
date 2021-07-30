package mentortools;

import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from training_class")
public class TrainingClassControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @RepeatedTest(2)
    void listTrainingClassTest() {

        TrainingClassDTO trainingClassDTO =
                template.postForObject("/api/trainingclass", new CreateTrainingClassCommand("Beginner Java Programmer",
                        LocalDate.of(2021, 5, 1),
                        LocalDate.of(2021, 8, 1)),
                        TrainingClassDTO.class);

        assertEquals("Beginner Java Programmer", trainingClassDTO.getTitle());

        template.postForObject("/api/trainingclass", new CreateTrainingClassCommand("Beginner C++ Programmer",
                        LocalDate.of(2021, 7, 1),
                        LocalDate.of(2021, 10, 1)),
                TrainingClassDTO.class);

        List<TrainingClassDTO> trainingClassDTOList = template.exchange("/api/trainingclass",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<TrainingClassDTO>>() {})
                .getBody();

        assertEquals(2,trainingClassDTOList.size());
    }

}
