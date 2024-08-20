package pro.arcodeh.collation_server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;

import java.util.HashSet;
import java.util.Set;

public class Lga {
    @Id
    private Integer id;
    private String name;
    private String abbreviation;
    @Transient
    State state;
    private Set<Ward> wards = new HashSet<>();
    @Version
    private Integer version;

    public Lga (Integer id, String name, String abbreviation, int version) {
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Set<Ward> getWards() {
        return wards;
    }

    public void setWards(Set<Ward> wards) {
        this.wards = wards;
    }

    public void addWard(Ward ward) {
        this.wards.add(ward);
        ward.lga = this;
    }
}
