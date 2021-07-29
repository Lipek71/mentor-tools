package mentortools;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/trainingclass")
@Tag(name = "Operations on training class.")
public class TrainingClassController {

    final private TrainingClassService trainingClassService;

    public TrainingClassController(TrainingClassService trainingClassService) {
        this.trainingClassService = trainingClassService;
    }

    @GetMapping
    @Operation(summary = "List training classes.")
    public List<TrainingClassDTO> getAuthors(){
        return trainingClassService.getTrainingClass();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates an training class.")
    @ApiResponse(responseCode = "201", description = "Training class has been created.")
    public TrainingClassDTO createAuthor(@RequestBody CreateTrainingClassCommand command){
        return trainingClassService.createTrainingClass(command);
    }
}
