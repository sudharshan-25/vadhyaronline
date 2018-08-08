package in.ssi.vadhyaronline.entity;

import in.ssi.vadhyaronline.domain.AbstractResponse;

import javax.persistence.*;

@Entity
@Table(name = "soothram_master")
public class SoothramMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "soothram_id")
    private int soothramId;

    @Column(name = "soothram_name", unique = true)
    private String soothramName;

    @Column(name = "approved")
    private boolean approved;

    @Column(name = "requested_by")
    private Integer requestedBy;

    @Column(name = "approved_by")
    private Integer approvedBy;

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

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public int getRequestedBy() {
        return requestedBy == null ? 0 : requestedBy;
    }

    public void setRequestedBy(Integer requestedBy) {
        this.requestedBy = requestedBy;
    }

    public int getApprovedBy() {
        return approvedBy == null ? 0 : approvedBy;
    }

    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
    }

    public AbstractResponse toDomain() {
        return new AbstractResponse(this.soothramId, this.soothramName);
    }
}
