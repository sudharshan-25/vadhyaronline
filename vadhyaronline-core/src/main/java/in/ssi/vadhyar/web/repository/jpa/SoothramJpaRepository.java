package in.ssi.vadhyar.web.repository.jpa;

import in.ssi.vadhyar.web.entity.SimpleUserEntity;
import in.ssi.vadhyar.web.entity.SoothramEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoothramJpaRepository extends JpaRepository<SoothramEntity, Integer> {

    List<SoothramEntity> findAllByApproved(boolean approved);

    List<SoothramEntity> findAllByRequestedBy(SimpleUserEntity requestedBy);

}
