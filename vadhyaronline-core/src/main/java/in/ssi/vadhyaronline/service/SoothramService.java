package in.ssi.vadhyaronline.service;

import in.ssi.vadhyaronline.authentication.LoginUserContext;
import in.ssi.vadhyaronline.dao.SoothramMasterRepository;
import in.ssi.vadhyaronline.domain.AbstractResponse;
import in.ssi.vadhyaronline.entity.SoothramMasterEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoothramService {

    private SoothramMasterRepository soothramRepository;

    private LoginUserContext loginUserContext;

    public SoothramService(SoothramMasterRepository soothramMasterRepository, LoginUserContext loginUserContext){
        this.soothramRepository = soothramMasterRepository;
        this.loginUserContext = loginUserContext;
    }

    @Transactional(readOnly = true)
    public List<AbstractResponse> getAllSoothram() {
        return soothramRepository.findSoothramMasterEntitiesByApprovedIsTrue()
                .stream().map(SoothramMasterEntity::toDomain).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void addSoothram(String soothramName) {
        SoothramMasterEntity soothramMaster = new SoothramMasterEntity();
        soothramMaster.setSoothramName(soothramName);
        soothramMaster.setApproved(false);
        soothramMaster.setRequestedBy(loginUserContext.getCurrentUser().getUserId());
        soothramRepository.save(soothramMaster);
    }

    @Transactional(readOnly = true)
    public boolean doesSoothramExist(String soothram) {
        return soothramRepository.existsBySoothramName(soothram);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateSoothram(int soothramId, String soothramName) {
        SoothramMasterEntity soothramMaster = soothramRepository.getOne(soothramId);
        soothramMaster.setSoothramName(soothramName);
        soothramRepository.save(soothramMaster);
    }

    @Transactional(readOnly = true)
    public List<AbstractResponse> unApprovedSoothrams(){
        return soothramRepository.findSoothramMasterEntitiesByApprovedIsFalse()
                .stream().map(SoothramMasterEntity::toDomain).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void approveSoothrams(List<AbstractResponse> soothrams) {
        List<Integer> soothramIds = soothrams.stream().map(AbstractResponse::getId).collect(Collectors.toList());
        List<SoothramMasterEntity> unapproved = soothramRepository.findAllById(soothramIds);
        int approvedBy = loginUserContext.getCurrentUser().getUserId();
        unapproved.forEach(soothram -> {
            soothram.setApproved(true);
            soothram.setApprovedBy(approvedBy);
        });
        soothramRepository.saveAll(unapproved);
    }

}
