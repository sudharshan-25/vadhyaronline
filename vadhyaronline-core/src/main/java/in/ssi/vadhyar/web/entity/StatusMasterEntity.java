package in.ssi.vadhyar.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status_master")
public class StatusMasterEntity {

    @Id
    @Column(name = "status_master_id")
    private int statusMasterId;

    @Column(name = "status_master_name", unique = true)
    private String statusMasterName;

    public int getStatusMasterId() {
        return statusMasterId;
    }

    public void setStatusMasterId(int statusMasterId) {
        this.statusMasterId = statusMasterId;
    }

    public String getStatusMasterName() {
        return statusMasterName;
    }

    public void setStatusMasterName(String statusMasterName) {
        this.statusMasterName = statusMasterName;
    }
}
