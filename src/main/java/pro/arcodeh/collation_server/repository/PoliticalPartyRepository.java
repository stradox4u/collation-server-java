package pro.arcodeh.collation_server.repository;

import org.springframework.data.repository.ListCrudRepository;
import pro.arcodeh.collation_server.model.PoliticalParty;

public interface PoliticalPartyRepository extends ListCrudRepository<PoliticalParty, Integer> {
    PoliticalParty findByName(String name);
}
