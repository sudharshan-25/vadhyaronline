package in.ssi.vadhyaronline.dao;

import in.ssi.vadhyaronline.entity.EventCategoryEntity;
import in.ssi.vadhyaronline.entity.EventTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventTypeRepository extends JpaRepository<EventTypeEntity, Integer> {

    List<EventTypeEntity> findEventTypeEntitiesByEventCategory(EventCategoryEntity eventCategoryEntity);
}
