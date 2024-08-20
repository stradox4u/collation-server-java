package pro.arcodeh.collation_server.repository;

import org.springframework.data.repository.ListCrudRepository;
import pro.arcodeh.collation_server.model.Ward;

public interface WardRepository extends ListCrudRepository<Ward, Integer> {
}
