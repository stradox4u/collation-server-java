package pro.arcodeh.collation_server.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import pro.arcodeh.collation_server.model.ElectionPoliticalPartyPollingUnitResult;
import pro.arcodeh.collation_server.result.ResultWithPartyName;

import java.util.List;
import java.util.UUID;

public interface ElectionPoliticalPartyPollingUnitResultRepository extends ListCrudRepository<ElectionPoliticalPartyPollingUnitResult, UUID> {
    List<ElectionPoliticalPartyPollingUnitResult> findByElection(UUID electionId);

    List<ElectionPoliticalPartyPollingUnitResult> findByElectionAndPollingUnit(UUID electionId, Integer pollingUnitId);

    @Query("SELECT SUM(election_political_party_polling_unit_result.vote_count) AS vote_count, political_party.name as party_name FROM election_political_party_polling_unit_result INNER JOIN election_political_party ON election_political_party_polling_unit_result.entrant = election_political_party.id INNER JOIN political_party ON election_political_party.political_party = political_party.id WHERE election_political_party_polling_unit_result.election = :electionId GROUP BY political_party.name")
    List<ResultWithPartyName> loadElectionResults(UUID electionId);
}
