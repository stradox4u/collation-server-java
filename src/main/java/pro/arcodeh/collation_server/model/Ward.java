package pro.arcodeh.collation_server.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Ward implements Persistable<Integer>, Serializable {
    private Integer id;
    private String name;
    private String abbreviation;
    @Transient
    Lga lga;
    @Transient
    private boolean forceCreate = false;
    private Set<PollingUnit> pollingUnits = new HashSet<>();

    public Ward(Integer id, String name, String abbreviation, boolean forceCreate) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.forceCreate = forceCreate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Set<PollingUnit> getPollingUnits() {
        return pollingUnits;
    }

    public void setPollingUnits(Set<PollingUnit> pollingUnits) {
        this.pollingUnits = pollingUnits;
    }

    public void addPollingUnit(PollingUnit pollingUnit) {
        this.pollingUnits.add(pollingUnit);
        pollingUnit.ward = this;
    }

    @Override
    public boolean isNew() {
        return this.forceCreate;
    }
}
