package SCD.Sprint_Tracker.DTO;

import lombok.Data;

@Data
public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private String assignee;
    private String status;
    private Long sprintId;
}