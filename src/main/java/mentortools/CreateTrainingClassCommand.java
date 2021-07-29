package mentortools;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTrainingClassCommand {

    @Schema(description = "Trainig class name", example = "Beginner Java programmer")
    String title;


}