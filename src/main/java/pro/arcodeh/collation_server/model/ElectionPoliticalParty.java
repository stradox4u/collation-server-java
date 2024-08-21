package pro.arcodeh.collation_server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ElectionPoliticalParty {
    @Id
    private Integer id;
    private AggregateReference<Election, UUID> election;
    private AggregateReference<PoliticalParty, Integer> politicalParty;
    private LocalDateTime createdAt;
    @Version
    private Integer version;

    public ElectionPoliticalParty(AggregateReference<Election, UUID> election, AggregateReference<PoliticalParty, Integer> politicalParty) {
        this.election = election;
        this.politicalParty = politicalParty;
        this.createdAt = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AggregateReference<Election, UUID> getElection() {
        return election;
    }

    public void setElection(AggregateReference<Election, UUID> election) {
        this.election = election;
    }

    public AggregateReference<PoliticalParty, Integer> getPoliticalParty() {
        return politicalParty;
    }

    public void setPoliticalParty(AggregateReference<PoliticalParty, Integer> politicalParty) {
        this.politicalParty = politicalParty;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
