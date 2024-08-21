package pro.arcodeh.collation_server.party;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.arcodeh.collation_server.model.PoliticalParty;

import java.util.List;

@RestController
@RequestMapping("/party")
public class PartyController {
    private final PartyService partyService;

    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/")
    public List<PoliticalParty> getParties() {
        return this.partyService.getAllParties();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/{id}")
    public PoliticalParty getPartyById(Integer id) {
        return this.partyService.getParty(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public PoliticalParty createParty(@RequestBody PoliticalParty party) {
        return this.partyService.createParty(party);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{id}")
    public PoliticalParty updateParty(@PathVariable Integer id, @Valid @RequestBody PoliticalParty party) {
        return this.partyService.updateParty(id, party);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteParty(@PathVariable Integer id) {
        this.partyService.deleteParty(id);
    }
}
