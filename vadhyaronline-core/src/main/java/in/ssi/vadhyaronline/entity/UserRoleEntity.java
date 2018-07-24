package in.ssi.vadhyaronline.entity;

import in.ssi.vadhyaronline.domain.UserRole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRoleEntity {

    @Id
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

    public UserRole toDomain() {
        return new UserRole(this.roleId, this.roleName);
    }
}
