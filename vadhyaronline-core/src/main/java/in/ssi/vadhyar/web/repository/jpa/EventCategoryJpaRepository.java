package in.ssi.vadhyar.web.repository.jpa;

import in.ssi.vadhyar.web.entity.EventCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventCategoryJpaRepository extends JpaRepository<EventCategoryEntity, Integer> {

    List<EventCategoryEntity> findAllByApproved(Boolean approved);

    List<EventCategoryEntity> findAllByRequestedBy(int requestedBy);

}
