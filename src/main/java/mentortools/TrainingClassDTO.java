package mentortools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingClassDTO {

    private Long id;

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;
}
