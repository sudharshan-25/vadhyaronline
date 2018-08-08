package in.ssi.vadhyaronline.entity;

import in.ssi.vadhyaronline.domain.AbstractResponse;

import javax.persistence.*;

@Entity
@Table(name = "veda_master")
public class VedaMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "veda_id")
    private int vedaMasterId;

    @Column(name = "veda_name", unique = true)
    private String vedaMasterName;

    public int getVedaMasterId() {
        return vedaMasterId;
    }

    public void setVedaMasterId(int vedaMasterId) {
        this.vedaMasterId = vedaMasterId;
    }

    public String getVedaMasterName() {
        return vedaMasterName;
    }

    public void setVedaMasterName(String vedaMasterName) {
        this.vedaMasterName = vedaMasterName;
    }

    public AbstractResponse toDomain() {
        return new AbstractResponse(this.vedaMasterId, this.vedaMasterName);
    }
}
