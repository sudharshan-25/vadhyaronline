package in.ssi.vadhyaronline.repository;

import in.ssi.vadhyaronline.entity.EventCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventCategoryRepository extends JpaRepository<EventCategoryEntity, Integer> {

    List<EventCategoryEntity> findEventCategoryEntitiesByApprovedIsFalse();

    List<EventCategoryEntity> findEventCategoryEntitiesByApprovedIsTrue();

    List<EventCategoryEntity> findEventCategoryEntitiesByRequestedBy(int requestedBy);
}
