package in.ssi.vadhyaronline.entity;

import in.ssi.vadhyaronline.domain.AbstractResponse;

import javax.persistence.*;

@Entity
@Table(name = "gothram_master")
public class GothramMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gothram_id")
    private int gothramId;

    @Column(name = "gothram_name", unique = true)
    private String gothramName;

    @Column(name = "approved")
    private boolean approved;

    @Column(name = "requested_by")
    private Integer requestedBy;

    @Column(name = "approved_by")
    private Integer approvedBy;

    public int getGothramId() {
        return gothramId;
    }

    public void setGothramId(int gothramId) {
        this.gothramId = gothramId;
    }

    public String getGothramName() {
        return gothramName;
    }

    public void setGothramName(String gothramName) {
        this.gothramName = gothramName;
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
        return new AbstractResponse(this.gothramId, this.gothramName);
    }
}
