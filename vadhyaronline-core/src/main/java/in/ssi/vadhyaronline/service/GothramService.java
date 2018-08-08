package in.ssi.vadhyaronline.service;

import in.ssi.vadhyaronline.authentication.LoginUserContext;
import in.ssi.vadhyaronline.repository.GothramMasterRepository;
import in.ssi.vadhyaronline.domain.Gotharam;
import in.ssi.vadhyaronline.entity.GothramMasterEntity;
import in.ssi.vadhyaronline.exception.VadhyarOnlineException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GothramService {

    private GothramMasterRepository gothramRepository;

    private LoginUserContext loginUserContext;

    public GothramService(GothramMasterRepository gothramRepository, LoginUserContext loginUserContext) {
        this.gothramRepository = gothramRepository;
        this.loginUserContext = loginUserContext;
    }

    @Transactional(readOnly = true)
    public List<Gotharam> getAllGothram() {
        return gothramRepository.findGothramMasterEntitiesByApprovedIsTrue()
                .stream().map(GothramMasterEntity::toDomain).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void addGothram(Gotharam gothram) throws VadhyarOnlineException {
        if (doesGothramExist(gothram.getGothramName())) {
            throw new VadhyarOnlineException("Gothram Name already exists");
        }
        GothramMasterEntity gothramMaster = new GothramMasterEntity();
        gothramMaster.setGothramName(gothram.getGothramName());
        gothramMaster.setApproved(gothram.isApproved());
        gothramRepository.save(gothramMaster);
    }

    @Transactional(readOnly = true)
    public boolean doesGothramExist(String gothram) {
        return gothramRepository.existsByGothramName(gothram);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateGothram(int gothramId, String gothramName) throws VadhyarOnlineException {
        if (doesGothramExist(gothramName)) {
            throw new VadhyarOnlineException("Gothram Name already exists");
        }
        GothramMasterEntity gothramMaster = gothramRepository.getOne(gothramId);
        gothramMaster.setGothramName(gothramName);
        gothramRepository.save(gothramMaster);
    }

    @Transactional(readOnly = true)
    public List<Gotharam> getUnapprovedGothrams() {
        return gothramRepository.findGothramMasterEntitiesByApprovedIsFalse()
                .stream().map(GothramMasterEntity::toDomain).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void approveGothrams(List<Gotharam> gothrams) {
        List<Integer> gothramIds = gothrams.stream().map(Gotharam::getGothramId).collect(Collectors.toList());
        List<GothramMasterEntity> unapproved = gothramRepository.findAllById(gothramIds);
        int approvedBy = loginUserContext.getCurrentUser().getUserId();
        unapproved.forEach(soothram -> {
            soothram.setApproved(true);
            soothram.setApprovedBy(approvedBy);
        });
        gothramRepository.saveAll(unapproved);
    }

    @Transactional(readOnly = true)
    public List<Gotharam> getRequestedByGothrams() {
        int requestedBy = loginUserContext.getCurrentUser().getUserId();
        return gothramRepository.findAllByRequestedBy(requestedBy)
                .stream().map(GothramMasterEntity::toDomain).collect(Collectors.toList());
    }

}
