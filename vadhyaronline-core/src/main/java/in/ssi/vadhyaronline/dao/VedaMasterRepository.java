package in.ssi.vadhyaronline.dao;

import in.ssi.vadhyaronline.entity.VedaMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VedaMasterRepository extends JpaRepository<VedaMasterEntity, Integer> {
}
