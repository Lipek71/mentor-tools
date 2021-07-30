package mentortools;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/trainingclass")
@Tag(name = "Operations on training class.")
public class TrainingClassController {

    final private TrainingClassService trainingClassService;

    public TrainingClassController(TrainingClassService trainingClassService) {
        this.trainingClassService = trainingClassService;
    }

    /*@GetMapping
    @Operation(summary = "List all training classes.")
    public List<TrainingClassDTO> getTrainingClass(){
        return trainingClassService.getTrainingClass();
    }*/

    @GetMapping
    @Operation(summary = "List conditioned training classes.")
    public List<TrainingClassDTO> listTrainingClass(@RequestParam Optional<String> title, @RequestParam Optional<String> startDate, @RequestParam Optional<String> endDate) {
        return trainingClassService.listTrainingClass(title, startDate, endDate);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a training class.")
    @ApiResponse(responseCode = "201", description = "Training class has been created.")
    public TrainingClassDTO createTrainingClass(@Valid @RequestBody CreateTrainingClassCommand command){
        return trainingClassService.createTrainingClass(command);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a training class.")
    @Transactional
    public TrainingClassDTO updateTrainingClass(@PathVariable("id") long id, @Valid @RequestBody UpdateTrainingClassCommand command) {
        return trainingClassService.updateTrainingClass(id, command);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a training class.")
    public void deleteTrainingClass(@PathVariable("id") long id){
        trainingClassService.deleteTrainingClass(id);
    }

    @DeleteMapping("/all")
    @Operation(summary = "Delete all training classes.")
    public void deleteTrainingClassAll(){
        trainingClassService.deleteTrainingClassAll();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException iae) {
        Problem problem =
                Problem.builder()
                        .withType(URI.create("training_class/not-found"))
                        .withTitle("Not found")
                        .withStatus(Status.NOT_FOUND)
                        .withDetail(iae.getMessage())
                        .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Problem> handleValidException(MethodArgumentNotValidException e) {
        List<Violation> violations =
                e.getBindingResult().getFieldErrors().stream()
                        .map(fe -> new Violation(fe.getField(), fe.getDefaultMessage()))
                        .collect(Collectors.toList());

        Problem problem =
                Problem.builder()
                        .withType(URI.create("training_class_title/not-valid"))
                        .withTitle("Validation error")
                        .withStatus(Status.BAD_REQUEST)
                        .withDetail(e.getMessage())
                        .with("violations", violations)
                        .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

}
