package in.ssi.vadhyar.web.service;

import in.ssi.vadhyar.web.domain.UserDetails;
import in.ssi.vadhyar.web.entity.RoleMasterEntity;
import in.ssi.vadhyar.web.entity.UserMasterEntity;
import in.ssi.vadhyar.web.exception.VadhyarOnlineException;
import in.ssi.vadhyar.web.repository.jpa.UserJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserJpaRepository userJpaRepository;

    private ValidationService validationService;

    public UserService(UserJpaRepository userJpaRepository, ValidationService validationService) {
        this.userJpaRepository = userJpaRepository;
        this.validationService = validationService;
    }

    public List<UserDetails> getAllUsers() {
        return userJpaRepository.findAll().stream().map(UserMasterEntity::toDomain).collect(Collectors.toList());
    }

    public UserDetails getUserById(int userId) {
        return userJpaRepository.getOne(userId).toDomain();
    }

    public void createUser(UserDetails userDetails) {
        UserMasterEntity userMasterEntity = new UserMasterEntity();
        userMasterEntity.setUserFirstName(userDetails.getFirstName());
        userMasterEntity.setUserLastName(userDetails.getLastName());
        if (!validationService.isUniqueUserName(userDetails.getUserName())) {
            throw new VadhyarOnlineException("UserName is not unique...");
        }
        userMasterEntity.setUserName(userDetails.getUserName());
        userMasterEntity.setPassword(userDetails.getPassword());

        if (!validationService.isUniqueUserName(userDetails.getEmail())) {
            throw new VadhyarOnlineException("Email is not unique...");
        }
        userMasterEntity.setUserEmail(userDetails.getEmail());

        if (!validationService.isUniqueUserName(userDetails.getMobile())) {
            throw new VadhyarOnlineException("Mobile number is not unique...");
        }
        userMasterEntity.setUserMobile(userDetails.getMobile());

        RoleMasterEntity roleMasterEntity = new RoleMasterEntity();
        roleMasterEntity.setRoleId(Integer.parseInt(userDetails.getRole()));
        userMasterEntity.setRoleMasterEntity(roleMasterEntity);

        userJpaRepository.save(userMasterEntity);
    }

}
