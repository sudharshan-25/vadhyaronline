package in.ssi.vadhyaronline.repository;

import in.ssi.vadhyaronline.entity.UserMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMasterRepository extends JpaRepository<UserMasterEntity, Integer> {

    /**
     * Find user master entity by userName
     *
     * @param userName userName
     * @return user master <code>{@link UserMasterEntity}</code>
     */
    UserMasterEntity findUserMasterEntityByUserName(String userName);

}
