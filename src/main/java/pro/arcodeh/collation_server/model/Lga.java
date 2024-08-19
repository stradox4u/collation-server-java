package pro.arcodeh.collation_server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Lga implements Persistable<Integer>, Serializable {
    @Id
    private Integer id;
    private String name;
    private String abbreviation;
    @Transient
    State state;
    private Set<Ward> wards = new HashSet<>();
    @Transient
    private boolean forceNew = false;

    public Lga (Integer id, String name, String abbreviation, boolean forceNew) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.forceNew = forceNew;
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

    @Override
    public boolean isNew() {
        return this.forceNew;
    }
}
