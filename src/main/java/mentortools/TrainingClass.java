package mentortools;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "training_class")
public class TrainingClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    public TrainingClass(String title) {
        this.title = title;
    }

    public TrainingClass(String title, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
