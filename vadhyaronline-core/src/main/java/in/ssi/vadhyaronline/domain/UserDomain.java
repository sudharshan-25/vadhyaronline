package in.ssi.vadhyaronline.domain;

public class UserDomain {

    private Integer userId;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private String email;

    private String mobile;

    private String gothram;

    private String soothram;

    private String veda;

    private String status;

    private String role;

    public UserDomain() {
    }

    public UserDomain(Integer userId, String firstName, String lastName, String userName, String email,
                      String mobile, String status, String role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.mobile = mobile;
        this.status = status;
        this.setRole(role);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public String getGothram() {
        return gothram;
    }

    public void setGothram(String gothram) {
        this.gothram = gothram;
    }

    public String getSoothram() {
        return soothram;
    }

    public void setSoothram(String soothram) {
        this.soothram = soothram;
    }

    public String getVeda() {
        return veda;
    }

    public void setVeda(String veda) {
        this.veda = veda;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
