package in.ssi.vadhyar.web.entity;

import javax.persistence.*;

@Entity
@Table(name = "veda_master")
public class VedaMasterEntity {

    @Id
    @Column(name = "veda_id")
    private int vedaId;

    @Column(name = "veda_name")
    private String vedaName;

    public int getVedaId() {
        return vedaId;
    }

    public void setVedaId(int vedaId) {
        this.vedaId = vedaId;
    }

    public String getVedaName() {
        return vedaName;
    }

    public void setVedaName(String vedaName) {
        this.vedaName = vedaName;
    }
}
