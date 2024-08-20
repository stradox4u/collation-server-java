package pro.arcodeh.collation_server.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.util.HashSet;
import java.util.Set;

public class State {
    @Id
    private Integer id;
    private String name;
    private Set<Lga> lgas = new HashSet<>();
    @Version
    private int version;

    public State(int id, String name, int version) {
        this.id = id;
        this.name = name;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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
}
