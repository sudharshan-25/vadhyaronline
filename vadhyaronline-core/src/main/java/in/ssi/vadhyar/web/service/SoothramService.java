package in.ssi.vadhyar.web.service;

import in.ssi.vadhyar.web.authentication.LoginUserContext;
import in.ssi.vadhyar.web.domain.Soothram;
import in.ssi.vadhyar.web.entity.SimpleUserEntity;
import in.ssi.vadhyar.web.entity.SoothramEntity;
import in.ssi.vadhyar.web.exception.VadhyarOnlineException;
import in.ssi.vadhyar.web.repository.jdbc.SoothramJdbcRepository;
import in.ssi.vadhyar.web.repository.jpa.SoothramJpaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoothramService {

    private SoothramJdbcRepository soothramJdbcRepository;

    private SoothramJpaRepository soothramJpaRepository;

    private LoginUserContext loginUserContext;

    public SoothramService(SoothramJdbcRepository soothramJdbcRepository, SoothramJpaRepository soothramJpaRepository,
                           LoginUserContext loginUserContext) {
        this.soothramJpaRepository = soothramJpaRepository;
        this.soothramJdbcRepository = soothramJdbcRepository;
        this.loginUserContext = loginUserContext;
    }

    public List<Soothram> findAllSoothrams() {
        return soothramJpaRepository.findAll().stream().map(SoothramEntity::toDomain).collect(Collectors.toList());
    }

    public List<Soothram> findAllApproved() {
        return soothramJpaRepository.findAllByApproved(Boolean.TRUE)
                .stream().map(SoothramEntity::toDomain).collect(Collectors.toList());
    }

    public List<Soothram> findAllUnApproved() {
        return soothramJpaRepository.findAllByApproved(Boolean.FALSE)
                .stream().map(SoothramEntity::toDomain).collect(Collectors.toList());
    }

    public List<Soothram> findAllRequested() {
        return soothramJpaRepository.findAllByRequestedBy(loginUserContext.getCurrentUser().getUserId())
                .stream().map(SoothramEntity::toDomain).collect(Collectors.toList());
    }

    public Soothram findSoothram(Integer soothramId) {
        return soothramJpaRepository.getOne(soothramId).toDomain();
    }

    public void createSoothram(Soothram soothram) {
        if (soothramJdbcRepository.isExistsByKey(soothram.getSoothramName()))
            throw new VadhyarOnlineException("Soothram Name already exists...");
        SoothramEntity soothramEntity = new SoothramEntity();
        soothramEntity.setApproved(Boolean.FALSE);
        soothramEntity.setRequestedBy(SimpleUserEntity.of(loginUserContext.getCurrentUser().getUserId()));
        soothramEntity.setRequestedOn(Timestamp.from(Instant.now()));
        soothramEntity.setSoothramName(soothram.getSoothramName());
        soothramJpaRepository.save(soothramEntity);
    }

    public void updateSoothram(Soothram soothram) {
        if (soothramJdbcRepository.isExistsByKey(soothram.getSoothramName()))
            throw new VadhyarOnlineException("Soothram Name already exists...");
        soothramJdbcRepository.updateSoothram(soothram);
    }

    public void approveSoothram(Integer soothramId) {
        soothramJdbcRepository.approveSoothram(soothramId);
    }

    public void deleteSoothram(Integer soothramId) {
        if (soothramJdbcRepository.canDelete(soothramId)) {
            soothramJpaRepository.deleteById(soothramId);
            return;
        }
        throw new VadhyarOnlineException("Links already exists. Cannot delete Soothram...");
    }

}
