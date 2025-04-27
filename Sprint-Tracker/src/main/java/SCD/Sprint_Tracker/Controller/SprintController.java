package SCD.Sprint_Tracker.Controller;

import SCD.Sprint_Tracker.DTO.SprintCreateDto;
import SCD.Sprint_Tracker.Entity.Sprint;
import SCD.Sprint_Tracker.Service.SprintService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprints")
public class SprintController {

    private final SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Sprint> createSprint(@RequestBody SprintCreateDto dto) {
        return ResponseEntity.ok(sprintService.createSprint(dto));
    }

    @GetMapping
    public ResponseEntity<List<Sprint>> getAll() {
        return ResponseEntity.ok(sprintService.getAllSprints());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sprint> getById(@PathVariable Long id) {
        return ResponseEntity.ok(sprintService.getSprintById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Sprint> updateSprint(@PathVariable Long id, @RequestBody SprintCreateDto dto) {
        return ResponseEntity.ok(sprintService.updateSprint(id, dto));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSprint(@PathVariable Long id) {
        sprintService.deleteSprint(id);
        return ResponseEntity.noContent().build();
    }
}
