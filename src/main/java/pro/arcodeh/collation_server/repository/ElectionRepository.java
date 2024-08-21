package pro.arcodeh.collation_server.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import pro.arcodeh.collation_server.model.Election;
import pro.arcodeh.collation_server.model.ElectionPoliticalParty;
import pro.arcodeh.collation_server.model.ElectionPoliticalPartyPollingUnitResult;
import pro.arcodeh.collation_server.model.ElectionPollingUnit;

import java.util.List;
import java.util.UUID;

public interface ElectionRepository extends ListCrudRepository<Election, UUID> {
    @Query("SELECT * FROM election_political_party WHERE election = :electionId")
    List<ElectionPoliticalParty> loadPoliticalParties(UUID electionId);

    @Query("SELECT * FROM election_polling_unit WHERE election = :electionId")
    List<ElectionPollingUnit> loadPollingUnits(UUID electionId);
}
