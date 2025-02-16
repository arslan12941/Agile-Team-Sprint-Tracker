package SCD.Sprint_Tracker.Service;

import SCD.Sprint_Tracker.DTO.SprintCreateDto;
import SCD.Sprint_Tracker.Entity.Sprint;
import SCD.Sprint_Tracker.Entity.Enums.SprintStatus;
import SCD.Sprint_Tracker.Repository.SprintRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SprintService {

    private final SprintRepository sprintRepository;

    public SprintService(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    public Sprint createSprint(SprintCreateDto dto) {
        Sprint sprint = new Sprint();
        sprint.setName(dto.getName());
        sprint.setGoal(dto.getGoal());
        sprint.setStartDate(dto.getStartDate());
        sprint.setEndDate(dto.getEndDate());
        sprint.setStatus(SprintStatus.PLANNED);
        return sprintRepository.save(sprint);
    }

    public List<Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }

    public Sprint getSprintById(Long id) {
        return sprintRepository.findById(Long.valueOf(String.valueOf(id)))
                .orElseThrow(() -> new RuntimeException("Sprint not found"));
    }

    public Sprint updateSprint(Long id, SprintCreateDto dto) {
        Sprint sprint = getSprintById(id);
        sprint.setName(dto.getName());
        sprint.setGoal(dto.getGoal());
        sprint.setStartDate(dto.getStartDate());
        sprint.setEndDate(dto.getEndDate());
        return sprintRepository.save(sprint);
    }

    public void deleteSprint(Long id) {
        sprintRepository.deleteById(Long.valueOf(String.valueOf(id)));
    }
}