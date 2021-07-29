package mentortools;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TrainingClassService {
    private TrainingClassRepository trainingClassRepository;

    private ModelMapper modelMapper;

    public List<TrainingClassDTO> getTrainingClass() {
        return trainingClassRepository.findAll().stream()
                .map(a->modelMapper.map(a, TrainingClassDTO.class))
                .collect(Collectors.toList());
    }

    public TrainingClassDTO createTrainingClass(CreateTrainingClassCommand command) {
        TrainingClass trainingClass = new TrainingClass(command.getTitle());

        trainingClassRepository.save(trainingClass);

        return modelMapper.map(trainingClass, TrainingClassDTO.class);
    }
}
