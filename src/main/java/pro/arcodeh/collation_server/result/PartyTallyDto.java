package pro.arcodeh.collation_server.result;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record PartyTallyDto(
        @Min(0)
        Integer voteCount,
        @Min(0)
        Integer entrant
) {
}
