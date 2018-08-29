package in.ssi.vadhyar.web.domain;

import in.ssi.vadhyar.web.constants.CommonConstants;

public class LoginUser {

    public LoginUser() {
    }

    public LoginUser(int userId, String userName, String role) {
        this();
        this.userId = userId;
        this.userName = userName;
        this.role = role;
    }

    private int userId;

    private String userName;

    private String role;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAdmin() {
        return CommonConstants.RoleConstants.ADMIN_ROLE_NAME.equalsIgnoreCase(role);
    }

    public boolean isVadhyar() {
        return CommonConstants.RoleConstants.VADHYAR_ROLE_NAME.equalsIgnoreCase(role);
    }

    public boolean isUser() {
        return CommonConstants.RoleConstants.USER_ROLE_NAME.equalsIgnoreCase(role);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
