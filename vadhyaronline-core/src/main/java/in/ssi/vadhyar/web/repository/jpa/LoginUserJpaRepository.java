package in.ssi.vadhyar.web.repository.jpa;

import in.ssi.vadhyar.web.entity.LoginUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginUserJpaRepository extends JpaRepository<LoginUserEntity, Integer> {

    LoginUserEntity findByUserName(String userName);
}
