package in.ssi.vadhyar.web.repository.jpa;

import in.ssi.vadhyar.web.entity.GothramEntity;
import in.ssi.vadhyar.web.entity.SimpleUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GothramJpaRepository extends JpaRepository<GothramEntity, Integer> {

    List<GothramEntity> findAllByApproved(boolean approved);

    List<GothramEntity> findAllByRequestedBy(SimpleUserEntity requestedBy);

}
