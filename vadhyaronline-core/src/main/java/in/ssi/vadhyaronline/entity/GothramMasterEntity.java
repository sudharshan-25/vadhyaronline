package in.ssi.vadhyaronline.entity;

import in.ssi.vadhyaronline.domain.AbstractResponse;

import javax.persistence.*;

@Entity
@Table(name = "gothram_master")
public class GothramMasterEntity implements AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gothram_id")
    private int gothramId;

    @Column(name = "gothram_name", unique = true)
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

    @Override
    public AbstractResponse toDomain() {
        return new AbstractResponse(this.gothramId, this.gothramName);
    }
}
