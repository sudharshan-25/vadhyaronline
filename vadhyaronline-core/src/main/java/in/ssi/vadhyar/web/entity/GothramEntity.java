package in.ssi.vadhyar.web.entity;

import in.ssi.vadhyar.web.domain.Gothram;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "gothram_master")
public class GothramEntity extends AbstractApprovalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gothram_id")
    private int gothramId;

    @Column(name = "gothram_name")
    private String gothramName;

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

    public Gothram toDomain() {
        return new Gothram(gothramId, gothramName, isApproved(),
                getRequestedBy() != null ? getRequestedBy().getUserName() : "",
                getApprovedBy() != null ? getApprovedBy().getUserName() : "");
    }
}
