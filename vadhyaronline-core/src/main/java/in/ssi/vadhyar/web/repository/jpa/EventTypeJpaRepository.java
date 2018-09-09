package in.ssi.vadhyar.web.repository.jpa;

import in.ssi.vadhyar.web.entity.EventTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventTypeJpaRepository extends JpaRepository<EventTypeEntity, Integer> {

    List<EventTypeEntity> findAllByApproved(boolean approved);

    List<EventTypeEntity> findByRequestedBy(int requestedBy);
}
