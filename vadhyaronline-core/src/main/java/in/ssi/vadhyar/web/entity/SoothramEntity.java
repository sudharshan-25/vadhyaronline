package in.ssi.vadhyar.web.entity;

import in.ssi.vadhyar.web.domain.Soothram;

import javax.persistence.*;

@Entity
@Table(name = "soothram_master")
public class SoothramEntity extends AbstractApprovalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "soothram_id")
    private int soothramId;

    @Column(name = "soothram_name")
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

    public Soothram toDomain() {
        return new Soothram(this.soothramId, soothramName, isApproved(),
                getRequestedBy() != null ? getRequestedBy().getUserName() : "",
                getApprovedBy() != null ? getApprovedBy().getUserName() : "");
    }

}
