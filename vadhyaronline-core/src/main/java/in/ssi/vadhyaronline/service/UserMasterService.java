package in.ssi.vadhyaronline.service;

import in.ssi.vadhyaronline.dao.GothramMasterRepository;
import in.ssi.vadhyaronline.dao.SoothramMasterRepository;
import in.ssi.vadhyaronline.dao.UserMasterRepository;
import in.ssi.vadhyaronline.dao.VedaMasterRepository;
import in.ssi.vadhyaronline.domain.UserDomain;
import in.ssi.vadhyaronline.entity.UserMasterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserMasterService {

    @Autowired
    private UserMasterRepository userRepository;

    @Autowired
    private VedaMasterRepository vedaMasterRepository;

    @Autowired
    private SoothramMasterRepository soothramMasterRepository;

    @Autowired
    private GothramMasterRepository gothramMasterRepository;

    @Transactional(readOnly = true)
    public List<UserDomain> getAllUsers() {
        return userRepository.findAll().stream().map(UserMasterEntity::toDomain).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserDomain userDomain) {
        Integer vedaId = StringUtils.isEmpty(userDomain.getVeda()) ?
                0 : Integer.parseInt(userDomain.getVeda());
        Integer soothramId = StringUtils.isEmpty(userDomain.getSoothram()) ?
                0 : Integer.parseInt(userDomain.getSoothram());
        Integer gothramId = StringUtils.isEmpty(userDomain.getGothram()) ?
                0 : Integer.parseInt(userDomain.getGothram());

        UserMasterEntity userMaster = new UserMasterEntity();
        userMaster.setFirstName(userDomain.getFirstName());
        userMaster.setLastName(userDomain.getLastName());
        userMaster.setUserName(userDomain.getUserName());
        userMaster.setEmail(userDomain.getEmail());
        userMaster.setPassword(userDomain.getPassword());
        userMaster.setMobile(userDomain.getMobile());
        if (vedaId != 0) {
            userMaster.setVedaMaster(vedaMasterRepository.getOne(vedaId));
        }
        if (soothramId != 0) {
            userMaster.setSoothramMaster(soothramMasterRepository.getOne(soothramId));
        }
        if (gothramId != 0) {
            userMaster.setGothramMaster(gothramMasterRepository.getOne(gothramId));
        }
        userRepository.save(userMaster);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

}
