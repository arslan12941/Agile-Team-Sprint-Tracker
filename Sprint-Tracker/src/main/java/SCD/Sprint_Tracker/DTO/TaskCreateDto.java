package SCD.Sprint_Tracker.DTO;

import lombok.Data;

@Data
public class TaskCreateDto {
    private String title;
    private String description;
    private Long sprintId;
}