package mentortools;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TrainingClassService {
    private TrainingClassRepository trainingClassRepository;

    private ModelMapper modelMapper;

    /*public List<TrainingClassDTO> getTrainingClass() {
        return trainingClassRepository.findAll().stream()
                .map(t->modelMapper.map(t, TrainingClassDTO.class))
                .collect(Collectors.toList());
    }*/

    public List<TrainingClassDTO> listTrainingClass(Optional<String> title, Optional<String> startDate, Optional<String> endDate) {
        return trainingClassRepository.findAll().stream()
                .filter(t -> title.isEmpty() || t.getTitle().toLowerCase().contains(title.get().toLowerCase()))
                .filter(t -> startDate.isEmpty() || t.getStartDate().equals(startDate))
                .filter(t -> endDate.isEmpty() || t.getEndDate().toString().equals(endDate))
                .map(t -> modelMapper.map(t, TrainingClassDTO.class))
                .collect(Collectors.toList());
    }

    public TrainingClassDTO createTrainingClass(CreateTrainingClassCommand command) {
/*
        DateInterval dateInterval = new DateInterval(command.getStartDate(), command.getEndDate());
        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(dateInterval);
        TrainingClass trainingClass = new TrainingClass(command.getTitle(), dateIntervalDTO.getDateInterval().getStartDate(), dateIntervalDTO.getDateInterval().getEndDate());
*/
        TrainingClass trainingClass = new TrainingClass(command.getTitle(), command.getStartDate(), command.getEndDate());

        trainingClassRepository.save(trainingClass);

        return modelMapper.map(trainingClass, TrainingClassDTO.class);
    }

    public TrainingClassDTO updateTrainingClass(long id, UpdateTrainingClassCommand command) {
        TrainingClass trainingClass = trainingClassRepository.getById(id);
        if(trainingClass == null){
            throw new IllegalArgumentException("Training class not found!" + id);
        }
        if (!trainingClass.getTitle().equals(command.getTitle())
            || !trainingClass.getStartDate().isEqual(command.getStartDate())
            || !trainingClass.getEndDate().isEqual(command.getEndDate())){

            trainingClass.setTitle(command.getTitle());
            trainingClass.setEndDate(command.getEndDate());
            trainingClass.setStartDate(command.getStartDate());
            trainingClassRepository.setTrainingClass(id, command.getTitle(), command.getEndDate(), command.getStartDate());
        }
        return modelMapper.map(trainingClass, TrainingClassDTO.class);
    }

    public void deleteTrainingClass(long id) {
        TrainingClass trainingClass = trainingClassRepository.getById(id);
        if(trainingClass == null){
            throw new IllegalArgumentException("Training class not found!" + id);
        }
        trainingClassRepository.deleteById(id);
    }

    public void deleteTrainingClassAll() {
        trainingClassRepository.deleteAll();
    }

}
