package SCD.Sprint_Tracker.Entity;

import SCD.Sprint_Tracker.Entity.Enums.SprintStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String goal;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks;
    private SprintStatus sprintStatus;

    public void setStatus(SprintStatus sprintStatus) {
        this.sprintStatus = sprintStatus;
    }
}