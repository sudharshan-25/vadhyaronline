package in.ssi.vadhyar.web.entity;

import in.ssi.vadhyar.web.domain.Gothram;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "gothram_master")
public class GothramEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gothram_id")
    private int gothramId;

    @Column(name = "gothram_name")
    private String gothramName;

    @Column(name = "approved")
    private boolean approved;

    @Column(name = "requested_by")
    private Integer requestedBy;

    @Column(name = "requested_on")
    private Timestamp requestedOn;

    @Column(name = "approved_by")
    private Integer approvedBy;

    @Column(name = "approved_on")
    private Timestamp approvedOn;

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

    public Integer getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(Integer requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Timestamp getRequestedOn() {
        return requestedOn;
    }

    public void setRequestedOn(Timestamp requestedOn) {
        this.requestedOn = requestedOn;
    }

    public Integer getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Timestamp getApprovedOn() {
        return approvedOn;
    }

    public void setApprovedOn(Timestamp approvedOn) {
        this.approvedOn = approvedOn;
    }

    public Gothram toDomain() {
        return new Gothram(gothramId, gothramName, approved, "" + requestedBy, ""+ approvedBy);
    }
}
