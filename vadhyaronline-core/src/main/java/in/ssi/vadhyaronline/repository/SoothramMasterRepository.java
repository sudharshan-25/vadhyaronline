package in.ssi.vadhyaronline.repository;

import in.ssi.vadhyaronline.entity.SoothramMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoothramMasterRepository extends JpaRepository<SoothramMasterEntity, Integer> {

    boolean existsBySoothramName(String soothramName);

    List<SoothramMasterEntity> findSoothramMasterEntitiesByApprovedIsFalse();

    List<SoothramMasterEntity> findSoothramMasterEntitiesByApprovedIsTrue();

    List<SoothramMasterEntity> findAllByRequestedBy(int requestedBy);
}
