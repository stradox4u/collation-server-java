package pro.arcodeh.collation_server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDate;
import java.util.UUID;

public class ElectionPollingUnit {
    @Id
    private Integer id;
    private AggregateReference<Election, UUID> election;
    private AggregateReference<PollingUnit, Integer> pollingUnit;
    private LocalDate createdAt;
    @Version
    private Integer version;

    public ElectionPollingUnit(AggregateReference<Election, UUID> election, AggregateReference<PollingUnit, Integer> pollingUnit) {
        this.election = election;
        this.pollingUnit = pollingUnit;
        this.createdAt = LocalDate.now();
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

    public AggregateReference<PollingUnit, Integer> getPollingUnit() {
        return pollingUnit;
    }

    public void setPollingUnit(AggregateReference<PollingUnit, Integer> pollingUnit) {
        this.pollingUnit = pollingUnit;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
