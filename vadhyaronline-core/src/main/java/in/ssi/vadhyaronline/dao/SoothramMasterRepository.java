package in.ssi.vadhyaronline.dao;

import in.ssi.vadhyaronline.entity.SoothramMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoothramMasterRepository extends JpaRepository<SoothramMasterEntity, Integer> {

    boolean existsBySoothramName(String soothramName);
}
