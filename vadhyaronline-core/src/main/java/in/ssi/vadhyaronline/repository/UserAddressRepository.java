package in.ssi.vadhyaronline.repository;

import in.ssi.vadhyaronline.entity.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Integer> {
}
