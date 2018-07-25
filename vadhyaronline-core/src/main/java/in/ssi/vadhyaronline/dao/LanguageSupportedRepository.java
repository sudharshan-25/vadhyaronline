package in.ssi.vadhyaronline.dao;

import in.ssi.vadhyaronline.entity.LanguageSupportedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageSupportedRepository extends JpaRepository<LanguageSupportedEntity, Integer> {

    List<LanguageSupportedEntity> findLanguageSupportedEntitiesByKeyIsLike(String key);
}
