package SCD.Sprint_Tracker.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class SprintResponseDto {
    private Long id;
    private String name;
    private String goal;
    private LocalDate startDate;
    private LocalDate endDate;
}