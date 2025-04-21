package SCD.Sprint_Tracker.Controller;

import SCD.Sprint_Tracker.DTO.TaskResponseDto;
import SCD.Sprint_Tracker.Entity.Enums.TaskStatus;
import SCD.Sprint_Tracker.Entity.Task;
import SCD.Sprint_Tracker.Service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/sprint/{sprintId}")
    public ResponseEntity<Task> createTask(@PathVariable Long sprintId, @RequestBody TaskResponseDto dto) {
        return ResponseEntity.ok(taskService.createTask(dto, sprintId));
    }

    @GetMapping("/sprint/{sprintId}")
    public ResponseEntity<List<Task>> getBySprint(@PathVariable Long sprintId) {
        return ResponseEntity.ok(taskService.getTasksBySprint(sprintId));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'DEVELOPER')")
    @PatchMapping("/{taskId}/status")
    public ResponseEntity<Task> updateStatus(@PathVariable Long taskId, @RequestParam TaskStatus status) {
        return ResponseEntity.ok(taskService.updateTaskStatus(taskId, status));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.getTask(taskId));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}