package in.ssi.vadhyaronline.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_login_status")
public class UserLoginStatusEntity {

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
    @JoinColumn(name = "login_status_id")
    private StatusMasterEntity statusMaster;

    @Column(name = "last_successful_login")
    private Timestamp lastSuccessfulLogin;

    @Column(name = "login_failed_attempt")
    private short loginFailedAttempts;

    public UserMasterEntity getUserMaster() {
        return userMaster;
    }

    public void setUserMaster(UserMasterEntity userMaster) {
        this.userMaster = userMaster;
    }

    public StatusMasterEntity getStatusMaster() {
        return statusMaster;
    }

    public void setStatusMaster(StatusMasterEntity statusMaster) {
        this.statusMaster = statusMaster;
    }

    public Timestamp getLastSuccessfulLogin() {
        return lastSuccessfulLogin;
    }

    public void setLastSuccessfulLogin(Timestamp lastSuccessfulLogin) {
        this.lastSuccessfulLogin = lastSuccessfulLogin;
    }

    public short getLoginFailedAttempts() {
        return loginFailedAttempts;
    }

    public void setLoginFailedAttempts(short loginFailedAttempts) {
        this.loginFailedAttempts = loginFailedAttempts;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
