package in.ssi.vadhyaronline.service;

import in.ssi.vadhyaronline.dao.RolesRepository;
import in.ssi.vadhyaronline.dao.StatusMasterRepository;
import in.ssi.vadhyaronline.dao.VedaMasterRepository;
import in.ssi.vadhyaronline.domain.AbstractResponse;
import in.ssi.vadhyaronline.entity.StatusMasterEntity;
import in.ssi.vadhyaronline.entity.UserRoleEntity;
import in.ssi.vadhyaronline.entity.VedaMasterEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MasterTableService {

    private VedaMasterRepository vedaRepository;

    private StatusMasterRepository statusRepository;

    private RolesRepository rolesRepository;

    public MasterTableService(VedaMasterRepository vedaRepository,
                              RolesRepository rolesRepository,
                              StatusMasterRepository statusRepository) {
        this.vedaRepository = vedaRepository;
        this.rolesRepository = rolesRepository;
        this.statusRepository = statusRepository;
    }


    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "veda")
    public List<AbstractResponse> getAllVeda() {
        return vedaRepository.findAll().stream().map(VedaMasterEntity::toDomain).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "status")
    public List<AbstractResponse> getAllStatusMaster() {
        return statusRepository.findAll().stream().map(StatusMasterEntity::toDomain).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "roles")
    public List<AbstractResponse> getAllRoles() {
        return rolesRepository.findAll().stream().map(UserRoleEntity::toDomain).collect(Collectors.toList());
    }

}
