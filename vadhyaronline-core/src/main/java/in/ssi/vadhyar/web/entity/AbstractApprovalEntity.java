package in.ssi.vadhyar.web.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractApprovalEntity {

    @Column(name = "approved")
    private boolean approved;

    @ManyToOne
    @JoinColumn(name = "requested_by")
    private SimpleUserEntity requestedBy;

    @Column(name = "requested_on")
    private Timestamp requestedOn;

    @ManyToOne
    @JoinColumn(name = "approved_by")
    private SimpleUserEntity approvedBy;

    @Column(name = "approved_on")
    private Timestamp approvedOn;

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public SimpleUserEntity getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(SimpleUserEntity requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Timestamp getRequestedOn() {
        return requestedOn;
    }

    public void setRequestedOn(Timestamp requestedOn) {
        this.requestedOn = requestedOn;
    }


    public SimpleUserEntity getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(SimpleUserEntity approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Timestamp getApprovedOn() {
        return approvedOn;
    }

    public void setApprovedOn(Timestamp approvedOn) {
        this.approvedOn = approvedOn;
    }
}
