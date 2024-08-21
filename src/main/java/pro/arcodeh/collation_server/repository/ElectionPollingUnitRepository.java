package pro.arcodeh.collation_server.repository;

import org.springframework.data.repository.ListCrudRepository;
import pro.arcodeh.collation_server.model.ElectionPollingUnit;

import java.util.UUID;

public interface ElectionPollingUnitRepository extends ListCrudRepository<ElectionPollingUnit, Integer> {
    void removeByElectionAndPollingUnit(UUID electionId, Integer pollingUnitId);
}
