package pro.arcodeh.collation_server.repository;

import org.springframework.data.repository.ListCrudRepository;
import pro.arcodeh.collation_server.model.State;

public interface StateRepository extends ListCrudRepository<State, Integer> {
}
