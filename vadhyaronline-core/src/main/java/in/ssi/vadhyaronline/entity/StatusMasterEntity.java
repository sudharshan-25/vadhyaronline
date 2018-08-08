package in.ssi.vadhyaronline.entity;

import in.ssi.vadhyaronline.domain.AbstractResponse;

import javax.persistence.*;

@Entity
@Table(name = "status_master")
public class StatusMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_master_id")
    private int statusId;

    @Column(name = "status_master_name", unique = true)
    private String statusName;

    public AbstractResponse toDomain() {
        return new AbstractResponse(this.getStatusId(), this.getStatusName());
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
