docpackage pro.arcodeh.collation_server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;

import java.util.HashSet;
import java.util.Set;

public class Ward {
    @Id
    private Integer id;
    private String name;
    private String abbreviation;
    @Transient
    Lga lga;
    @Version
    private int version;
    private Set<PollingUnit> pollingUnits = new HashSet<>();

    public Ward(Integer id, String name, String abbreviation, int version) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.version = version;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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
}
