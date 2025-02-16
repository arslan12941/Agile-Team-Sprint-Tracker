package SCD.Sprint_Tracker.Service;

import SCD.Sprint_Tracker.DTO.TaskResponseDto;
import SCD.Sprint_Tracker.Entity.Enums.TaskStatus;
import SCD.Sprint_Tracker.Entity.Task;
import SCD.Sprint_Tracker.Entity.Sprint;
import SCD.Sprint_Tracker.Repository.TaskRepository;
import SCD.Sprint_Tracker.Repository.SprintRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final SprintRepository sprintRepository;

    public TaskService(TaskRepository taskRepository, SprintRepository sprintRepository) {
        this.taskRepository = taskRepository;
        this.sprintRepository = sprintRepository;
    }

    public Task createTask(TaskResponseDto dto, Long sprintId) {
        Sprint sprint = sprintRepository.findById(Long.valueOf(String.valueOf(sprintId)))
                .orElseThrow(() -> new RuntimeException("Sprint not found"));

        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setAssignee(dto.getAssignee());
        task.setStatus(TaskStatus.TODO);
        task.setSprint(sprint);
        return taskRepository.save(task);
    }

    public List<Task> getTasksBySprint(Long sprintId) {
        return taskRepository.findBySprintId(Long.valueOf(String.valueOf(sprintId)));
    }

    public Task updateTaskStatus(Long taskId, TaskStatus status) {
        Task task = taskRepository.findById(Long.valueOf(String.valueOf(taskId)))
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(status);
        return taskRepository.save(task);
    }

    public Task getTask(Long taskId) {
        return taskRepository.findById(Long.valueOf(String.valueOf(taskId)))
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(Long.valueOf(String.valueOf(taskId)));
    }
}