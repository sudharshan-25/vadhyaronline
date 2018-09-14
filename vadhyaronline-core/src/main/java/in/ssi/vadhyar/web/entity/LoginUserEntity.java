package in.ssi.vadhyar.web.entity;

import in.ssi.vadhyar.web.domain.LoginUser;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_master")
public class LoginUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private RoleMasterEntity role;

    @ManyToOne
    @JoinColumn(name = "login_status_id")
    private StatusMasterEntity loginStatus;

    @Column(name = "latest_login_token")
    private String latestLoginToken;

    @Column(name = "last_successful_login")
    private Timestamp lastSuccessfulLogin;

    @Column(name = "login_failed_attempt")
    private Integer loginFailedAttempt;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleMasterEntity getRole() {
        return role;
    }

    public void setRole(RoleMasterEntity role) {
        this.role = role;
    }

    public StatusMasterEntity getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(StatusMasterEntity loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getLatestLoginToken() {
        return latestLoginToken;
    }

    public void setLatestLoginToken(String latestLoginToken) {
        this.latestLoginToken = latestLoginToken;
    }

    public Timestamp getLastSuccessfulLogin() {
        return lastSuccessfulLogin;
    }

    public void setLastSuccessfulLogin(Timestamp lastSuccessfulLogin) {
        this.lastSuccessfulLogin = lastSuccessfulLogin;
    }

    public Integer getLoginFailedAttempt() {
        return loginFailedAttempt;
    }

    public void setLoginFailedAttempt(Integer loginFailedAttempt) {
        this.loginFailedAttempt = loginFailedAttempt;
    }

    public LoginUser toDomain() {
        return new LoginUser(userId, userName, role == null ? "" : role.getRoleName(), latestLoginToken);
    }

}
