package pro.arcodeh.collation_server.result;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Component;
import pro.arcodeh.collation_server.NotFoundException;
import pro.arcodeh.collation_server.WebsocketService;
import pro.arcodeh.collation_server.model.Election;
import pro.arcodeh.collation_server.model.ElectionPoliticalParty;
import pro.arcodeh.collation_server.model.ElectionPoliticalPartyPollingUnitResult;
import pro.arcodeh.collation_server.model.ElectionPollingUnit;
import pro.arcodeh.collation_server.repository.ElectionPoliticalPartyPollingUnitResultRepository;
import pro.arcodeh.collation_server.repository.ElectionPoliticalPartyRepository;
import pro.arcodeh.collation_server.repository.ElectionPollingUnitRepository;
import pro.arcodeh.collation_server.repository.ElectionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ResultService {
    private final ElectionPoliticalPartyPollingUnitResultRepository resultRepository;
    private final ElectionRepository elections;
    private final ElectionPollingUnitRepository pollingUnits;
    private final ElectionPoliticalPartyRepository politicalParties;
    private final WebsocketService websocketService;

    public ResultService(ElectionPoliticalPartyPollingUnitResultRepository resultRepository, ElectionRepository elections, ElectionPollingUnitRepository pollingUnits, ElectionPoliticalPartyRepository politicalParties, WebsocketService websocketService) {
        this.resultRepository = resultRepository;
        this.elections = elections;
        this.pollingUnits = pollingUnits;
        this.politicalParties = politicalParties;
        this.websocketService = websocketService;
    }

    public List<ElectionPoliticalPartyPollingUnitResult> getElectionResults(UUID electionId) {
        return resultRepository.findByElection(electionId);
    }

    public List<ElectionPoliticalPartyPollingUnitResult> savePollingUnitResult(UUID electionId, ResultCreationDto results) {
        Election election = this.elections.findById(electionId).orElse(null);
        List<ElectionPoliticalPartyPollingUnitResult> resultsArr = new ArrayList<>();
        if(election == null) {
            throw new NotFoundException("Election with Id: " + electionId + " not found");
        }
        AggregateReference<Election, UUID> electionAgg = AggregateReference.to(election.getId());

        ElectionPollingUnit pollingUnit = this.pollingUnits.findById(results.pollingUnitId()).orElse(null);
        if(pollingUnit == null) {
            throw new NotFoundException("Polling unit with Id: " + results.pollingUnitId() + " not found");
        }
        AggregateReference<ElectionPollingUnit, Integer> puAgg = AggregateReference.to(pollingUnit.getId());

        for(PartyTallyDto partyTally : results.partyTallies()) {
            ElectionPoliticalParty politicalParty = this.politicalParties.findById(partyTally.entrant()).orElse(null);
            if(politicalParty == null) {
                throw new NotFoundException("Political party with Id: " + partyTally.entrant() + " not found");
            }
            AggregateReference<ElectionPoliticalParty, Integer> partyAgg = AggregateReference.to(politicalParty.getId());

            ElectionPoliticalPartyPollingUnitResult result = new ElectionPoliticalPartyPollingUnitResult(partyTally.voteCount(), electionAgg, partyAgg, puAgg);
            ElectionPoliticalPartyPollingUnitResult savedResult = this.resultRepository.save(result);
            resultsArr.add(savedResult);
        }

        this.updateAndSendResults(election);
        return resultsArr;
    }

    public List<ElectionPoliticalPartyPollingUnitResult> getPollingUnitResult (UUID electionId, Integer pollingUnitId) {
        return this.resultRepository.findByElectionAndPollingUnit(electionId, pollingUnitId);
    }

    private void updateAndSendResults(Election election) {
        List<ResultWithPartyName> results = this.resultRepository.loadElectionResults(election.getId());
        ResultUpdateDto resultUpdateDto = new ResultUpdateDto(election, results);
        // Send results to the collation server
        this.websocketService.sendResultsUpdate(resultUpdateDto);
    }
}
