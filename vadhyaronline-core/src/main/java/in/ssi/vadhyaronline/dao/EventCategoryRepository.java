package in.ssi.vadhyaronline.dao;

import in.ssi.vadhyaronline.entity.EventCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventCategoryRepository extends JpaRepository<EventCategoryEntity, Integer> {

    List<EventCategoryEntity> findEventCategoryEntitiesByCategoryName(String categoryName);
}
