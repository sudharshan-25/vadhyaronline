package in.ssi.vadhyaronline.entity;

import in.ssi.vadhyaronline.domain.UserDomain;

import javax.persistence.*;

@Entity
@Table(name = "user_master")
public class UserMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_first_name")
    private String firstName;

    @Column(name = "user_last_name")
    private String lastName;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_mobile")
    private String mobile;

    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private UserRoleEntity userRole;

    @ManyToOne
    @JoinColumn(name = "user_veda_id")
    private VedaMasterEntity vedaMaster;

    @ManyToOne
    @JoinColumn(name = "user_soothram_id")
    private SoothramMasterEntity soothramMaster;

    @ManyToOne
    @JoinColumn(name = "user_gothram_id")
    private GothramMasterEntity gothramMaster;

    @OneToOne(mappedBy = "userMaster", cascade = CascadeType.ALL)
    private UserLoginStatusEntity userLoginStatus;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public UserRoleEntity getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleEntity userRole) {
        this.userRole = userRole;
    }

    public VedaMasterEntity getVedaMaster() {
        return vedaMaster;
    }

    public void setVedaMaster(VedaMasterEntity vedaMaster) {
        this.vedaMaster = vedaMaster;
    }

    public SoothramMasterEntity getSoothramMaster() {
        return soothramMaster;
    }

    public void setSoothramMaster(SoothramMasterEntity soothramMaster) {
        this.soothramMaster = soothramMaster;
    }

    public GothramMasterEntity getGothramMaster() {
        return gothramMaster;
    }

    public void setGothramMaster(GothramMasterEntity gothramMaster) {
        this.gothramMaster = gothramMaster;
    }

    public UserDomain toDomain() {
        UserDomain user = new UserDomain(userId, firstName, lastName, userName, email, mobile,
                userLoginStatus.getStatusMaster().getStatusName(), this.getUserRole().getRoleName());
        if (soothramMaster != null) {
            user.setSoothram(soothramMaster.getSoothramName());
        }
        if (gothramMaster != null) {
            user.setGothram(gothramMaster.getGothramName());
        }
        if (vedaMaster != null) {
            user.setVeda(vedaMaster.getVedaMasterName());
        }
        return user;
    }

    public UserLoginStatusEntity getUserLoginStatus() {
        return userLoginStatus;
    }

    public void setUserLoginStatus(UserLoginStatusEntity userLoginStatus) {
        this.userLoginStatus = userLoginStatus;
    }

}
