package in.ssi.vadhyar.web.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user_details")
public class UserVaidhikamDetailsEntity {

    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name = "property", value = "userMaster"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "user_id")
    private int userId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private UserMasterEntity userMaster;

    @ManyToOne
    @JoinColumn(name = "user_veda_id")
    private VedaMasterEntity vedaMaster;

    @ManyToOne
    @JoinColumn(name = "user_soothram_id")
    private SoothramEntity soothram;

    @ManyToOne
    @JoinColumn(name = "user_gothram_id")
    private GothramEntity gothram;

}
