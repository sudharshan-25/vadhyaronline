package in.ssi.vadhyaronline.service;

import in.ssi.vadhyaronline.dao.RolesRepository;
import in.ssi.vadhyaronline.domain.AbstractResponse;
import in.ssi.vadhyaronline.entity.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServices {

    @Autowired
    private RolesRepository rolesRepository;

    /**
     * Get user roles
     *
     * @return list of user roles
     */
    public List<AbstractResponse> getUserRoles() {
        return rolesRepository.findAll().stream().map(UserRoleEntity::toDomain).collect(Collectors.toList());
    }
}
