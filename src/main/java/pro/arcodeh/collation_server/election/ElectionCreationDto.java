package pro.arcodeh.collation_server.election;

import pro.arcodeh.collation_server.model.Election;

import java.util.List;

public record ElectionCreationDto(Election election, List<Integer> partyIds, List<Integer> pollingUnitIds) {
}
