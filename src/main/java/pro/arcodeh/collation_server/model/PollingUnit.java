package pro.arcodeh.collation_server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

public class PollingUnit {
    @Id
    private Integer id;
    private String delimitation;
    private String puNumber;
    private String puName;
    private LocalDateTime createdAt;
    @Transient
    Ward ward;
    @Version
    private int version;

    public PollingUnit(Integer id, String delimitation, String puNumber, String puName, int version) {
        this.id = id;
        this.puNumber = puNumber;
        this.puName = puName;
        this.delimitation = delimitation;
        this.createdAt = LocalDateTime.now();
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDelimitation() {
        return delimitation;
    }

    public void setDelimitation(String delimitation) {
        this.delimitation = delimitation;
    }

    public String getPuNumber() {
        return puNumber;
    }

    public void setPuNumber(String puNumber) {
        this.puNumber = puNumber;
    }

    public String getPuName() {
        return puName;
    }

    public void setPuName(String puName) {
        this.puName = puName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }
}
