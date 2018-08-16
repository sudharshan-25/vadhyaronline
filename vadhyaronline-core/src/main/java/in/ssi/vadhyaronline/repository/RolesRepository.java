package in.ssi.vadhyaronline.repository;

import in.ssi.vadhyaronline.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<UserRoleEntity, Integer> {
}