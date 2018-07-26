package in.ssi.vadhyaronline.entity;

import in.ssi.vadhyaronline.domain.AbstractResponse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "soothram_master")
public class SoothramMasterEntity implements AbstractEntity {

    @Id
    @Column(name = "soothram_id")
    private int soothramId;

    @Column(name = "soothram_name", unique = true)
    private String soothramName;


    public int getSoothramId() {
        return soothramId;
    }

    public void setSoothramId(int soothramId) {
        this.soothramId = soothramId;
    }

    public String getSoothramName() {
        return soothramName;
    }

    public void setSoothramName(String soothramName) {
        this.soothramName = soothramName;
    }

    @Override
    public AbstractResponse toDomain() {
        return new AbstractResponse(this.soothramId, this.soothramName);
    }
}
