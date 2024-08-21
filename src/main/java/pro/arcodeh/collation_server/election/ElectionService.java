package pro.arcodeh.collation_server.election;

import org.springframework.stereotype.Component;
import pro.arcodeh.collation_server.model.*;
import pro.arcodeh.collation_server.repository.*;

import java.util.List;


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
        for (Integer partyId : partyIds) {
            PoliticalParty politicalParty = politicalParties.findById(partyId).orElse(null);
            ElectionPoliticalParty party = new ElectionPoliticalParty(savedElection, politicalParty);
            election.addPoliticalParty(party);
        }
        for (Integer puId : puIds) {
            PollingUnit pollingUnit = pollingUnits.findById(puId).orElse(null);
            ElectionPollingUnit pu = new ElectionPollingUnit(savedElection, pollingUnit);
            election.addPollingUnit(pu);
        }
        return elections.save(election);
    }

    public List<Election> getAllElections() {
        return elections.findAll();
    }

    public Election getElection(String id) {
        return elections.findById(id).orElse(null);
    }

    public Election updateElection(String id, Election election) {
        Election existingElection = elections.findById(id).orElse(null);
        if (existingElection == null) {
            return null;
        }
        existingElection.setElectionType(election.getElectionType());
        existingElection.setElectionDate(election.getElectionDate());
        return elections.save(existingElection);
    }

    public void deleteElection(String id) {
        elections.deleteById(id);
    }

    public void removePoliticalParty(String electionId, Integer politicalPartyId) {
        Election election = elections.findById(electionId).orElse(null);
        if (election == null) {
            return;
        }
        ElectionPoliticalParty politicalParty = electionPoliticalParties.findById(politicalPartyId).orElse(null);
        if (politicalParty == null) {
            return;
        }
        election.removePoliticalParty(politicalParty);
        elections.save(election);
    }

    public void removePollingUnit(String electionId, Integer pollingUnitId) {
        Election election = elections.findById(electionId).orElse(null);
        if (election == null) {
            return;
        }
        ElectionPollingUnit pollingUnit = electionPollingUnits.findById(pollingUnitId).orElse(null);
        if (pollingUnit == null) {
            return;
        }
        election.removePollingUnit(pollingUnit);
        elections.save(election);
    }
}
