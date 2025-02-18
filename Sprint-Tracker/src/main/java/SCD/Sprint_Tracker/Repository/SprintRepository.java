package SCD.Sprint_Tracker.Repository;

import SCD.Sprint_Tracker.Entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint, Long> {
}