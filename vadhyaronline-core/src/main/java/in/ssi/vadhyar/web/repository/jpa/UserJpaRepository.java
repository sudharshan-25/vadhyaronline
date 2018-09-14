package in.ssi.vadhyar.web.repository.jpa;

import in.ssi.vadhyar.web.entity.UserMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserMasterEntity, Integer> {
}
