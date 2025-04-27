package SCD.Sprint_Tracker.Repository;

import SCD.Sprint_Tracker.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findBySprintId(Long sprintId);
}