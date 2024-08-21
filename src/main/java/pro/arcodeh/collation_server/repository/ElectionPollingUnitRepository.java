package pro.arcodeh.collation_server.repository;

import org.springframework.data.repository.ListCrudRepository;
import pro.arcodeh.collation_server.model.ElectionPollingUnit;

public interface ElectionPollingUnitRepository extends ListCrudRepository<ElectionPollingUnit, Integer> {
}
