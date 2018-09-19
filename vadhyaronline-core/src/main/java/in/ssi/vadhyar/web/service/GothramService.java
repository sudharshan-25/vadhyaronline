package in.ssi.vadhyar.web.service;

import in.ssi.vadhyar.web.authentication.LoginUserContext;
import in.ssi.vadhyar.web.domain.Gothram;
import in.ssi.vadhyar.web.entity.GothramEntity;
import in.ssi.vadhyar.web.entity.SimpleUserEntity;
import in.ssi.vadhyar.web.exception.VadhyarOnlineException;
import in.ssi.vadhyar.web.repository.jdbc.GothramJdbcRepository;
import in.ssi.vadhyar.web.repository.jpa.GothramJpaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GothramService {

    private GothramJdbcRepository gothramJdbcRepository;

    private GothramJpaRepository gothramJpaRepository;

    private LoginUserContext loginUserContext;

    public GothramService(GothramJdbcRepository gothramJdbcRepository, GothramJpaRepository gothramJpaRepository,
                          LoginUserContext loginUserContext) {
        this.gothramJdbcRepository = gothramJdbcRepository;
        this.gothramJpaRepository = gothramJpaRepository;
        this.loginUserContext = loginUserContext;
    }

    public List<Gothram> findAllGothrams() {
        return gothramJpaRepository.findAll().stream().map(GothramEntity::toDomain).collect(Collectors.toList());
    }

    public List<Gothram> findAllApproved() {
        return gothramJpaRepository.findAllByApproved(Boolean.TRUE)
                .stream().map(GothramEntity::toDomain).collect(Collectors.toList());
    }

    public List<Gothram> findAllUnApproved() {
        return gothramJpaRepository.findAllByApproved(Boolean.FALSE)
                .stream().map(GothramEntity::toDomain).collect(Collectors.toList());
    }

    public List<Gothram> findAllRequested() {
        return gothramJpaRepository.findAllByRequestedBy(loginUserContext.getCurrentUser().getUserId())
                .stream().map(GothramEntity::toDomain).collect(Collectors.toList());
    }

    public Gothram findGothram(Integer gothramId){
        return gothramJpaRepository.getOne(gothramId).toDomain();
    }

    public void createGothram(Gothram gothram) {
        if(gothramJdbcRepository.isExistsByKey(gothram.getGothramName()))
            throw new VadhyarOnlineException("Gothram Name already exists...");
        GothramEntity gothramEntity = new GothramEntity();
        gothramEntity.setGothramName(gothram.getGothramName());
        gothramEntity.setApproved(Boolean.FALSE);
        gothramEntity.setRequestedOn(Timestamp.from(Instant.now()));
        gothramEntity.setRequestedBy(SimpleUserEntity.of(loginUserContext.getCurrentUser().getUserId()));
        gothramJpaRepository.save(gothramEntity);
    }

    public void updateGothram(Gothram gothram) {
        if(gothramJdbcRepository.isExistsByKey(gothram.getGothramName()))
            throw new VadhyarOnlineException("Gothram Name already exists...");
        gothramJdbcRepository.updateGothram(gothram);
    }

    public void approveGothram(Integer gothramId) {
        gothramJdbcRepository.approveGothram(gothramId);
    }

    public void deleteGothram(Integer gothramId) {
        if(gothramJdbcRepository.canDelete(gothramId)){
            gothramJpaRepository.deleteById(gothramId);
            return;
        }
        throw new VadhyarOnlineException("Links already exists. Cannot delete Gothram...");
    }
}
