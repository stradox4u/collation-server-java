package pro.arcodeh.collation_server.party;

import org.springframework.stereotype.Component;
import pro.arcodeh.collation_server.DuplicateEntityException;
import pro.arcodeh.collation_server.model.PoliticalParty;
import pro.arcodeh.collation_server.repository.PoliticalPartyRepository;

import java.util.List;

@Component
public class PartyService {
    private final PoliticalPartyRepository parties;

    public PartyService(PoliticalPartyRepository parties) {
        this.parties = parties;
    }

    public List<PoliticalParty> getAllParties() {
        return parties.findAll();
    }

    public PoliticalParty getParty(Integer id) {
        return parties.findById(id).orElse(null);
    }

    public PoliticalParty createParty(PoliticalParty party) {
        PoliticalParty existingParty = parties.findByName(party.getName());
        if (existingParty == null) {
            return parties.save(party);
        } else {
            throw new DuplicateEntityException("Political party with name " + party.getName() + " already exists");
        }
    }

    public PoliticalParty updateParty(Integer id, PoliticalParty party) {
        PoliticalParty existingParty = parties.findById(id).orElse(null);
        if (existingParty == null) {
            return null;
        }
        existingParty.setName(party.getName());
        return parties.save(existingParty);
    }

    public void deleteParty(Integer id) {
        parties.deleteById(id);
    }
}
