package mentortools;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTrainingClassCommand {

    @Schema(description = "Trainig class name", example = "Beginner Java programmer")
    @NotBlank(message = "Title can not be null or empty!")
    String title;

    @Schema(description = "Trainig class start date", example = "2021-05-01")
    private LocalDate startDate;

    @Schema(description = "Trainig class end date", example = "2021-06-30")
    private LocalDate endDate;
}
