package pro.arcodeh.collation_server.repository;

import org.springframework.data.repository.ListCrudRepository;
import pro.arcodeh.collation_server.model.ElectionPoliticalParty;

import java.util.UUID;

public interface ElectionPoliticalPartyRepository extends ListCrudRepository<ElectionPoliticalParty, Integer> {
    void removeByElectionAndPoliticalParty(UUID electionId, Integer politicalPartyId);
}
