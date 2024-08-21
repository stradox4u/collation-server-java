package pro.arcodeh.collation_server.result;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import pro.arcodeh.collation_server.model.ElectionPoliticalPartyPollingUnitResult;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/result")
public class ResultController {
    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping("/{electionId}")
    public List<ElectionPoliticalPartyPollingUnitResult> getElectionResults(@PathVariable UUID electionId) {
        return this.resultService.getElectionResults(electionId);
    }

    @GetMapping("/{electionId}/{pollingUnitId}")
    public List<ElectionPoliticalPartyPollingUnitResult> getPollingUnitResults(@PathVariable UUID electionId, @PathVariable Integer pollingUnitId) {
        return this.resultService.getPollingUnitResult(electionId, pollingUnitId);
    }

    @PostMapping("/{electionId}")
    public List<ElectionPoliticalPartyPollingUnitResult> savePollingUnitResult(@PathVariable UUID electionId, @Valid @RequestBody ResultCreationDto results) {
        return this.resultService.savePollingUnitResult(electionId, results);
    }
}
