package in.ssi.vadhyar.web.service;

import in.ssi.vadhyar.web.constants.CommonConstants;
import in.ssi.vadhyar.web.domain.LoginUser;
import in.ssi.vadhyar.web.entity.LoginUserEntity;
import in.ssi.vadhyar.web.entity.StatusMasterEntity;
import in.ssi.vadhyar.web.exception.VadhyarOnlineException;
import in.ssi.vadhyar.web.repository.helper.LoginStatusHelper;
import in.ssi.vadhyar.web.repository.jdbc.LoginUserJdbcRepository;
import in.ssi.vadhyar.web.repository.jpa.LoginUserJpaRepository;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

@Service
public class LoginService {

    private LoginUserJpaRepository loginUserJpaRepository;

    private LoginUserJdbcRepository loginUserJdbcRepository;

    private TokenService tokenService;

    public LoginService(TokenService tokenService, LoginUserJpaRepository loginUserJpaRepository,
                        LoginUserJdbcRepository loginUserJdbcRepository) {
        this.tokenService = tokenService;
        this.loginUserJpaRepository = loginUserJpaRepository;
        this.loginUserJdbcRepository = loginUserJdbcRepository;
    }

    public LoginUser login(String userName, String password) {
        LoginUserEntity loginUser = loginUserJpaRepository.findByUserName(userName);
        if (loginUser == null) {
            throw new VadhyarOnlineException("No such user exists ");
        }
        String actPassword = loginUser.getPassword();
        if (!Objects.equals(password, actPassword)) {
            int loginAttempts = loginUser.getLoginFailedAttempt();
            if (loginAttempts >= 3) {
                loginUserJdbcRepository.lockUser(loginUser.getUserId());
                throw new VadhyarOnlineException("User is locked");
            }
            loginAttempts++;
            loginUserJdbcRepository.updatedFailedAttempts(loginUser.getUserId(), loginAttempts);
            throw new VadhyarOnlineException("Invalid user credentials ");
        }

        StatusMasterEntity userLoginStatus = loginUser.getLoginStatus();
        int userStatus = userLoginStatus.getStatusMasterId();
        switch (userStatus) {
            case CommonConstants.StatusConstants.STATUS_LOCKED_ID:
                throw new VadhyarOnlineException("User is locked");
            case CommonConstants.StatusConstants.STATUS_INACTIVE_ID:
                throw new VadhyarOnlineException("User is inactive");
        }
        loginUser.setLatestLoginToken(tokenService.allocateToken(userName).getKey());
        loginUser.setLoginFailedAttempt(0);
        loginUser.setLoginStatus(LoginStatusHelper.getLoggedInStatus());
        loginUser.setLastSuccessfulLogin(Timestamp.from(Instant.now()));
        return loginUserJpaRepository.save(loginUser).toDomain();
    }

}
