package in.ssi.vadhyaronline.service;

import in.ssi.vadhyaronline.dao.RolesRepository;
import in.ssi.vadhyaronline.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServices {

    @Autowired
    private RolesRepository rolesRepository;

    /**
     * Get user roles
     *
     * @return list of user roles
     */
    public List<UserRole> getUserRoles() {
        List<UserRole> userRoles = new ArrayList<>();
        rolesRepository.findAll().forEach(userRoles::add);
        return userRoles;
    }
}
