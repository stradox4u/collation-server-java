package pro.arcodeh.collation_server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDateTime;
import java.util.UUID;

public class ElectionPoliticalPartyPollingUnitResult {
    @Id
    private UUID id;
    private Integer voteCount;
    private LocalDateTime createdAt;
    private AggregateReference<Election, UUID> election;
    private AggregateReference<ElectionPoliticalParty, Integer> entrant;
    AggregateReference<ElectionPollingUnit, Integer> pollingUnit;
    @Version
    private Integer version;

    public ElectionPoliticalPartyPollingUnitResult(Integer voteCount, AggregateReference<Election, UUID> election, AggregateReference<ElectionPoliticalParty, Integer> entrant, AggregateReference<ElectionPollingUnit, Integer> pollingUnit) {
        this.voteCount = voteCount;
        this.election = election;
        this.entrant = entrant;
        this.pollingUnit = pollingUnit;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public AggregateReference<Election, UUID> getElection() {
        return election;
    }

    public void setElection(AggregateReference<Election, UUID> election) {
        this.election = election;
    }

    public AggregateReference<ElectionPoliticalParty, Integer> getEntrant() {
        return entrant;
    }

    public void setEntrant(AggregateReference<ElectionPoliticalParty, Integer> entrant) {
        this.entrant = entrant;
    }

    public AggregateReference<ElectionPollingUnit, Integer> getPollingUnit() {
        return pollingUnit;
    }

    public void setPollingUnit(AggregateReference<ElectionPollingUnit, Integer> pollingUnit) {
        this.pollingUnit = pollingUnit;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
