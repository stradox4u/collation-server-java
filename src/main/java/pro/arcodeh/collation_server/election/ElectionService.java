package pro.arcodeh.collation_server.election;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Component;
import pro.arcodeh.collation_server.model.*;
import pro.arcodeh.collation_server.repository.*;

import java.util.List;
import java.util.UUID;


@Component
public class ElectionService {
    private final ElectionRepository elections;
    private final ElectionPoliticalPartyRepository electionPoliticalParties;
    private final ElectionPollingUnitRepository electionPollingUnits;
    private final PoliticalPartyRepository politicalParties;
    private final PollingUnitRepository pollingUnits;

    public ElectionService(ElectionRepository elections, ElectionPoliticalPartyRepository electionPoliticalParties, ElectionPollingUnitRepository electionPollingUnits, PoliticalPartyRepository politicalParties, PollingUnitRepository pollingUnits) {
        this.elections = elections;
        this.politicalParties = politicalParties;
        this.pollingUnits = pollingUnits;
        this.electionPoliticalParties = electionPoliticalParties;
        this.electionPollingUnits = electionPollingUnits;
    }

    public Election createElection(Election election, List<Integer> partyIds, List<Integer> puIds) {
        Election savedElection = elections.save(election);
        AggregateReference<Election, UUID> electionAgg = AggregateReference.to(savedElection.getId());
        for (Integer partyId : partyIds) {
            PoliticalParty politicalParty = this.politicalParties.findById(partyId).orElse(null);
            if(politicalParty == null) {
                throw new RuntimeException("Political party with Id: " + partyId + " not found");
            }
            AggregateReference<PoliticalParty, Integer> partyAgg = AggregateReference.to(politicalParty.getId());
            ElectionPoliticalParty elParty = new ElectionPoliticalParty(electionAgg, partyAgg);
            this.electionPoliticalParties.save(elParty);
        }
        for (Integer puId : puIds) {
            PollingUnit pollingUnit = this.pollingUnits.findById(puId).orElse(null);
            if(pollingUnit == null) {
                throw new RuntimeException("Polling unit with Id: " + puId + " not found");
            }
            AggregateReference<PollingUnit, Integer> puAgg = AggregateReference.to(pollingUnit.getId());
            ElectionPollingUnit pu = new ElectionPollingUnit(electionAgg, puAgg);
            this.electionPollingUnits.save(pu);
        }
        return savedElection;
    }

    public List<Election> getAllElections() {
        return this.elections.findAll();
    }

    public Election getElection(UUID id) {
        return this.elections.findById(id).orElse(null);
    }

    public Election updateElection(UUID id, Election election) {
        Election existingElection = this.elections.findById(id).orElse(null);
        if (existingElection == null) {
            return null;
        }
        existingElection.setElectionType(election.getElectionType());
        existingElection.setElectionDate(election.getElectionDate());
        return this.elections.save(existingElection);
    }

    public void deleteElection(UUID id) {
        this.elections.deleteById(id);
    }

    public void removePoliticalParty(UUID electionId, Integer politicalPartyId) {
        this.electionPoliticalParties.removeByElectionAndPoliticalParty(electionId, politicalPartyId);
    }

    public void removePollingUnit(UUID electionId, Integer pollingUnitId) {
        this.electionPollingUnits.removeByElectionAndPollingUnit(electionId, pollingUnitId);
    }
}
