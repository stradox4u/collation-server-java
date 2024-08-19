package pro.arcodeh.collation_server.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class State implements Persistable<Integer>, Serializable {
    @Id
    private Integer id;
    private String name;
    private Set<Lga> lgas = new HashSet<>();
    @Transient
    private boolean forceNew = false;

    public State(int id, String name, boolean forceNew) {
        this.id = id;
        this.name = name;
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

    public Set<Lga> getLgas() {
        return lgas;
    }

    public void setLgas(Set<Lga> lgas) {
        this.lgas = lgas;
    }

    public void addLga(Lga lga) {
        this.lgas.add(lga);
        lga.state = this;
    }

    @Override
    public String toString() {
        return "State{" +
                "state_id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean isNew() {
        return this.forceNew;
    }
}
