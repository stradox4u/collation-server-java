package pro.arcodeh.collation_server.repository;

import org.springframework.data.repository.ListCrudRepository;
import pro.arcodeh.collation_server.model.ElectionPoliticalPartyPollingUnitResult;

import java.util.List;
import java.util.UUID;

public interface ElectionPoliticalPartyPollingUnitResultRepository extends ListCrudRepository<ElectionPoliticalPartyPollingUnitResult, UUID> {
    List<ElectionPoliticalPartyPollingUnitResult> findByElection(UUID electionId);

    List<ElectionPoliticalPartyPollingUnitResult> findByElectionAndPollingUnit(UUID electionId, Integer pollingUnitId);
}
