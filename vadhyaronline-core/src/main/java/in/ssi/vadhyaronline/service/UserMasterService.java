package in.ssi.vadhyaronline.service;

import in.ssi.vadhyaronline.constants.CommonConstants;
import in.ssi.vadhyaronline.dao.*;
import in.ssi.vadhyaronline.domain.UserDomain;
import in.ssi.vadhyaronline.entity.UserLoginStatusEntity;
import in.ssi.vadhyaronline.entity.UserMasterEntity;
import in.ssi.vadhyaronline.entity.UserRoleEntity;
import in.ssi.vadhyaronline.exception.VadhyarOnlineException;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserMasterService {

    private UserMasterRepository userRepository;

    private StatusMasterRepository statusRepository;

    private VedaMasterRepository vedaMasterRepository;

    private SoothramMasterRepository soothramMasterRepository;

    private GothramMasterRepository gothramMasterRepository;

    private RolesRepository rolesRepository;

    private TokenService tokenService;

    public UserMasterService(UserMasterRepository userRepository, StatusMasterRepository statusRepository,
                             VedaMasterRepository vedaMasterRepository, RolesRepository rolesRepository,
                             SoothramMasterRepository soothramMasterRepository,
                             GothramMasterRepository gothramMasterRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
        this.vedaMasterRepository = vedaMasterRepository;
        this.rolesRepository = rolesRepository;
        this.soothramMasterRepository = soothramMasterRepository;
        this.gothramMasterRepository = gothramMasterRepository;
        this.tokenService = tokenService;
    }

    @Transactional(readOnly = true)
    public List<UserDomain> getAllUsers() {
        return userRepository.findAll().stream().map(UserMasterEntity::toDomain).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDomain getUserById(int userId) {
        return userRepository.getOne(userId).toDomain();
    }

    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserDomain userDomain) {
        UserMasterEntity userMaster = new UserMasterEntity();
        UserLoginStatusEntity userLoginStatus = new UserLoginStatusEntity();
        userLoginStatus.setLoginFailedAttempts(new Short("0"));
        userLoginStatus.setStatusMaster(EntityHelper.getActiveStatus());
        userLoginStatus.setUserMaster(userMaster);
        userMaster.setUserLoginStatus(userLoginStatus);
        updateUserEntityFromDomain(userDomain, userMaster);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserDomain userDomain) {
        UserMasterEntity userMaster = userRepository.getOne(userDomain.getUserId());
        UserLoginStatusEntity userLoginStatus = userMaster.getUserLoginStatus();
        userLoginStatus.setStatusMaster(statusRepository.getOne(1));
        userLoginStatus.setUserMaster(userMaster);
        userMaster.setUserLoginStatus(userLoginStatus);
        updateUserEntityFromDomain(userDomain, userMaster);
    }

    @Transactional(rollbackFor = Exception.class)
    public void inactivateUser(int userId) {
        UserMasterEntity userMaster = userRepository.getOne(userId);
        UserLoginStatusEntity userLoginStatus = userMaster.getUserLoginStatus();
        userLoginStatus.setStatusMaster(EntityHelper.getInactiveStatus());
        userRepository.save(userMaster);
    }

    @Transactional(rollbackFor = Exception.class)
    public void activateUser(int userId) {
        UserMasterEntity userMaster = userRepository.getOne(userId);
        UserLoginStatusEntity userLoginStatus = userMaster.getUserLoginStatus();
        userLoginStatus.setStatusMaster(EntityHelper.getActiveStatus());
        userRepository.save(userMaster);
    }

    @Transactional(rollbackFor = Exception.class)
    public void lockUser(UserMasterEntity userMaster) {
        UserLoginStatusEntity userLoginStatus = userMaster.getUserLoginStatus();
        userLoginStatus.setStatusMaster(EntityHelper.getLockedStatus());
        userRepository.save(userMaster);
    }

    public UserDomain login(String userName, String password) throws VadhyarOnlineException {

        UserMasterEntity userMaster = userRepository.findUserMasterEntityByUserName(userName);
        if (userMaster == null) {
            throw new VadhyarOnlineException("No such user exists ");
        }
        String actPassword = userMaster.getPassword();
        if (!Objects.equals(password, actPassword)) {
            int loginAttempts = userMaster.getUserLoginStatus().getLoginFailedAttempts();
            if (loginAttempts >= 3) {
                lockUser(userMaster);
                throw new VadhyarOnlineException("User is locked");
            }
            loginAttempts++;
            userMaster.getUserLoginStatus().setLoginFailedAttempts((short) loginAttempts);
            userRepository.save(userMaster);
            throw new VadhyarOnlineException("Invalid user credentials ");
        }
        UserLoginStatusEntity userLoginStatus = userMaster.getUserLoginStatus();
        int userStatus = userMaster.getUserLoginStatus().getStatusMaster().getStatusId();
        switch (userStatus) {
            case CommonConstants.StatusConstants.STATUS_LOCKED_ID:
                throw new VadhyarOnlineException("User is locked");
            case CommonConstants.StatusConstants.STATUS_INACTIVE_ID:
                throw new VadhyarOnlineException("User is inactive");
        }
        userLoginStatus.setLoginToken(tokenService.allocateToken(userName).getKey());
        userLoginStatus.setLoginFailedAttempts((short) 0);
        userLoginStatus.setStatusMaster(EntityHelper.getLoggedInStatus());
        userLoginStatus.setLastSuccessfulLogin(Timestamp.valueOf(LocalDateTime.now()));
        return userRepository.save(userMaster).toDomain();
    }

    private void updateUserEntityFromDomain(UserDomain userDomain, UserMasterEntity userMaster) {
        int vedaId = StringUtils.isEmpty(userDomain.getVeda()) ?
                0 : Integer.parseInt(userDomain.getVeda());
        int soothramId = StringUtils.isEmpty(userDomain.getSoothram()) ?
                0 : Integer.parseInt(userDomain.getSoothram());
        int gothramId = StringUtils.isEmpty(userDomain.getGothram()) ?
                0 : Integer.parseInt(userDomain.getGothram());

        Integer roleId = Integer.parseInt(userDomain.getRole());
        UserRoleEntity userRole = rolesRepository.getOne(roleId);
        userMaster.setUserRole(userRole);
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
}
