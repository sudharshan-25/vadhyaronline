package in.ssi.vadhyaronline.dao;

import in.ssi.vadhyaronline.entity.GothramMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GothramMasterRepository extends JpaRepository<GothramMasterEntity, Integer> {

    boolean existsByGothramName(String gothramName);

    List<GothramMasterEntity> findGothramMasterEntitiesByApprovedIsTrue();

    List<GothramMasterEntity> findGothramMasterEntitiesByApprovedIsFalse();

}
