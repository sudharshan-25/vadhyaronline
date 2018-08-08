package in.ssi.vadhyaronline.repository;

import in.ssi.vadhyaronline.entity.EventStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventStatusRepository extends JpaRepository<EventStatusEntity, Integer> {
}
