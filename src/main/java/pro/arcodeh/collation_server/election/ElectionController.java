package pro.arcodeh.collation_server.election;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.arcodeh.collation_server.model.Election;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/election")
public class ElectionController {
    private final ElectionService electionService;

    public ElectionController(ElectionService electionService) {
        this.electionService = electionService;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/")
    public List<Election> getElections() {
        return this.electionService.getAllElections();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/{id}")
    public Election getElectionById(@PathVariable UUID id) {
        return this.electionService.getElection(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public Election createElection(@Valid @RequestBody ElectionCreationDto dto) {
        return this.electionService.createElection(dto.election(), dto.partyIds(), dto.puIds());
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{id}")
    public Election updateElection(@PathVariable UUID id, @Valid @RequestBody Election election) {
        return this.electionService.updateElection(id, election);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteElection(@PathVariable UUID id) {
        this.electionService.deleteElection(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/{id}/political-party/{partyId}")
    public void removePartyFromElection(@PathVariable UUID id, @PathVariable Integer partyId) {
        this.electionService.removePoliticalParty(id, partyId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/{id}/polling-unit/{puId}")
    public void removePollingUnitFromElection(@PathVariable UUID id, @PathVariable Integer puId) {
        this.electionService.removePollingUnit(id, puId);
    }
}
