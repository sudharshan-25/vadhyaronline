package in.ssi.vadhyaronline.entity;

import in.ssi.vadhyaronline.domain.AbstractResponse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "veda_master")
public class VedaMasterEntity implements AbstractEntity {

    @Id
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

    @Override
    public AbstractResponse toDomain() {
        return new AbstractResponse(this.vedaMasterId, this.vedaMasterName);
    }
}
