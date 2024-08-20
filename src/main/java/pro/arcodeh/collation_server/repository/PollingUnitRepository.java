package pro.arcodeh.collation_server.repository;

import org.springframework.data.repository.ListCrudRepository;
import pro.arcodeh.collation_server.model.PollingUnit;

public interface PollingUnitRepository extends ListCrudRepository<PollingUnit, Integer> {
}
