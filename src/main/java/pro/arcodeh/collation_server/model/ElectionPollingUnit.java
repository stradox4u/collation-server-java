package pro.arcodeh.collation_server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDate;

public class ElectionPollingUnit {
    @Id
    private Integer id;
    Election election;
    PollingUnit pollingUnit;
    private LocalDate createdAt;
    @Version
    private Integer version;

    public ElectionPollingUnit(Election election, PollingUnit pollingUnit) {
        this.election = election;
        this.pollingUnit = pollingUnit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public PollingUnit getPollingUnit() {
        return pollingUnit;
    }

    public void setPollingUnit(PollingUnit pollingUnit) {
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
