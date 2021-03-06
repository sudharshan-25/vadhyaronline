package in.ssi.vadhyar.web.authentication;

import in.ssi.vadhyar.web.constants.CommonConstants;
import in.ssi.vadhyar.web.domain.LoginUser;
import org.springframework.stereotype.Service;

@Service
public class LoginUserContext {

    private ThreadLocal<LoginUser> currentUser = new InheritableThreadLocal<>();

    public LoginUser getCurrentUser() {
        return currentUser.get();
    }

    public void setCurrentUser(LoginUser userDomain) {
        currentUser.set(userDomain);
    }

}
