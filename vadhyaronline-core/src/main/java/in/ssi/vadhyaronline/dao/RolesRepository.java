package in.ssi.vadhyaronline.dao;

import in.ssi.vadhyaronline.entity.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends CrudRepository<UserRole, Integer> {
}
