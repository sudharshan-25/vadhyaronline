package in.ssi.vadhyaronline.service;

import in.ssi.vadhyaronline.dao.*;
import in.ssi.vadhyaronline.domain.AbstractResponse;
import in.ssi.vadhyaronline.entity.*;
import in.ssi.vadhyaronline.exception.VadhyarOnlineException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MasterTableService {

    private GothramMasterRepository gothramRepository;

    private SoothramMasterRepository soothramRepository;

    private VedaMasterRepository vedaRepository;

    private StatusMasterRepository statusRepository;

    private RolesRepository rolesRepository;

    public MasterTableService(GothramMasterRepository gothramRepository, SoothramMasterRepository soothramRepository,
                              VedaMasterRepository vedaRepository, RolesRepository rolesRepository,
                              StatusMasterRepository statusRepository) {
        this.gothramRepository = gothramRepository;
        this.soothramRepository = soothramRepository;
        this.vedaRepository = vedaRepository;
        this.rolesRepository = rolesRepository;
        this.statusRepository = statusRepository;
    }


    @Transactional(readOnly = true)
    public List<AbstractResponse> getAllGothram() {
        return gothramRepository.findAll().stream().map(GothramMasterEntity::toDomain).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void addGothram(String gothramName) throws VadhyarOnlineException {
        if (doesGothramExist(gothramName)) {
            throw new VadhyarOnlineException("Gothram Name already exists");
        }
        GothramMasterEntity gothramMaster = new GothramMasterEntity();
        gothramMaster.setGothramName(gothramName);
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
    public List<AbstractResponse> getAllSoothram() {
        return soothramRepository.findAll().stream().map(SoothramMasterEntity::toDomain).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void addSoothram(String soothramName) {
        SoothramMasterEntity soothramMaster = new SoothramMasterEntity();
        soothramMaster.setSoothramName(soothramName);
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
    public List<AbstractResponse> getAllVeda() {
        return vedaRepository.findAll().stream().map(VedaMasterEntity::toDomain).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AbstractResponse> getAllStatusMaster() {
        return statusRepository.findAll().stream().map(StatusMasterEntity::toDomain).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AbstractResponse> getAllRoles() {
        return rolesRepository.findAll().stream().map(UserRoleEntity::toDomain).collect(Collectors.toList());
    }

}
