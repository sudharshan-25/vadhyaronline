package in.ssi.vadhyaronline.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "user_role")
public class UserRole {

    @Column(name = "role_id")
    private int roleId;
    @Column(name = "role_name", unique = true)
    private String roleName;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
