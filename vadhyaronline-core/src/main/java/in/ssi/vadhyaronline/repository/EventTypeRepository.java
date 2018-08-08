package in.ssi.vadhyaronline.repository;

import in.ssi.vadhyaronline.entity.EventCategoryEntity;
import in.ssi.vadhyaronline.entity.EventTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventTypeRepository extends JpaRepository<EventTypeEntity, Integer> {

    List<EventTypeEntity> findEventTypeEntitiesByEventCategoryAndApprovedIsTrue(EventCategoryEntity eventCategoryEntity);

    List<EventTypeEntity> findAllByApprovedIsTrue();

    List<EventTypeEntity> findAllByApprovedIsFalse();

    List<EventTypeEntity> findAllByRequestedBy(int requestedBy);
}
