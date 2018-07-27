package in.ssi.vadhyaronline.dao;

import in.ssi.vadhyaronline.entity.UserMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMasterRepository extends JpaRepository<UserMasterEntity, Integer> {
}
