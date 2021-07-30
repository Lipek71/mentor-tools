package mentortools;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.time.LocalDate;

@Repository
@Table(name = "training_class")
public interface TrainingClassRepository extends JpaRepository<TrainingClass, Long> {

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("UPDATE TrainingClass t SET t.title = :title, t.endDate = :endDate, t.startDate = :startDate WHERE t.id = :id")
    void setTrainingClass(@Param("id") Long id, @Param("title") String title, @Param("endDate") LocalDate endDate, @Param("startDate") LocalDate startDate);


}
