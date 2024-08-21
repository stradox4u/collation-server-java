package pro.arcodeh.collation_server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

public class ElectionPoliticalPartyPollingUnitResult {
    @Id
    private String id;
    private Integer voteCount;
    private LocalDateTime createdAt;
    Election election;
    ElectionPoliticalParty entrant;
    ElectionPollingUnit pollingUnit;
    @Version
    private Integer version;

    public ElectionPoliticalPartyPollingUnitResult(Integer voteCount, Election election, ElectionPoliticalParty entrant, ElectionPollingUnit pollingUnit) {
        this.voteCount = voteCount;
        this.election = election;
        this.entrant = entrant;
        this.pollingUnit = pollingUnit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public ElectionPoliticalParty getEntrant() {
        return entrant;
    }

    public void setEntrant(ElectionPoliticalParty entrant) {
        this.entrant = entrant;
    }

    public ElectionPollingUnit getPollingUnit() {
        return pollingUnit;
    }

    public void setPollingUnit(ElectionPollingUnit pollingUnit) {
        this.pollingUnit = pollingUnit;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
