package pro.arcodeh.collation_server.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Election {
    @Id
    private String id;
    private String name;
    private String electionType;
    private LocalDate electionDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Version
    private Integer version;
    private Set<ElectionPollingUnit> pollingUnits = new HashSet<>();
    private Set<ElectionPoliticalParty> politicalParties = new HashSet<>();
    private Set<ElectionPoliticalPartyPollingUnitResult> results = new HashSet<>();

    @JsonCreator
    public Election(@JsonProperty("name") String name, @JsonProperty("electionType") String electionType, @JsonProperty("electionDate") LocalDate electionDate) {
        this.name = name;
        this.electionType = electionType;
        this.electionDate = electionDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElectionType() {
        return electionType;
    }

    public void setElectionType(String electionType) {
        this.electionType = electionType;
    }

    public LocalDate getElectionDate() {
        return electionDate;
    }

    public void setElectionDate(LocalDate electionDate) {
        this.electionDate = electionDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Set<ElectionPollingUnit> getPollingUnits() {
        return pollingUnits;
    }

    public void setPollingUnits(Set<ElectionPollingUnit> pollingUnits) {
        this.pollingUnits = pollingUnits;
    }

    public Set<ElectionPoliticalParty> getPoliticalParties() {
        return politicalParties;
    }

    public void setPoliticalParties(Set<ElectionPoliticalParty> politicalParties) {
        this.politicalParties = politicalParties;
    }

    public Set<ElectionPoliticalPartyPollingUnitResult> getResults() {
        return results;
    }

    public void setResults(Set<ElectionPoliticalPartyPollingUnitResult> results) {
        this.results = results;
    }

    public void addPollingUnit(ElectionPollingUnit pollingUnit) {
        this.pollingUnits.add(pollingUnit);
        pollingUnit.setElection(this);
    }

    public void removePollingUnit(ElectionPollingUnit pollingUnit) {
        this.pollingUnits.remove(pollingUnit);
        pollingUnit.setElection(null);
    }

    public void addPoliticalParty(ElectionPoliticalParty politicalParty) {
        this.politicalParties.add(politicalParty);
        politicalParty.setElection(this);
    }

    public void removePoliticalParty(ElectionPoliticalParty politicalParty) {
        this.politicalParties.remove(politicalParty);
        politicalParty.setElection(null);
    }

    public void addResult(ElectionPoliticalPartyPollingUnitResult result) {
        this.results.add(result);
        result.setElection(this);
    }
}
