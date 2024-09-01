package pro.arcodeh.collation_server.result;

import pro.arcodeh.collation_server.model.Election;

import java.util.List;

public record ResultUpdateDto (Election election, List<ResultWithPartyName> results) {
}
