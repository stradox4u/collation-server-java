package pro.arcodeh.collation_server.repository;

import org.springframework.data.repository.ListCrudRepository;
import pro.arcodeh.collation_server.model.Election;

public interface ElectionRepository extends ListCrudRepository<Election, String> {
}
