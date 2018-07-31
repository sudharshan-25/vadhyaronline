package in.ssi.vadhyaronline.dao;

import in.ssi.vadhyaronline.entity.StatusMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusMasterRepository extends JpaRepository<StatusMasterEntity, Integer> {
}
