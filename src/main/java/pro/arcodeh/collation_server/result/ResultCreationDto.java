package pro.arcodeh.collation_server.result;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ResultCreationDto(
        @Min(0)
        Integer pollingUnitId,
        @NotEmpty.List(value = @NotEmpty)
        List<PartyTallyDto> partyTallies
) {
}
