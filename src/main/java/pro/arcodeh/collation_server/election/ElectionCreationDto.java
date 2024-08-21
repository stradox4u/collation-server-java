package pro.arcodeh.collation_server.election;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import pro.arcodeh.collation_server.model.Election;

import java.time.LocalDate;
import java.util.List;

public record ElectionCreationDto(
        Election election,
        @Valid List<Integer> partyIds,
        @Valid List<Integer> puIds) {
}
