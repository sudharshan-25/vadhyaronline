package in.ssi.vadhyaronline.authentication;

import in.ssi.vadhyaronline.domain.LoginUser;
import in.ssi.vadhyaronline.domain.UserDomain;
import org.springframework.stereotype.Service;

@Service
public class LoginUserContext {

    private ThreadLocal<LoginUser> currentUser = new InheritableThreadLocal<>();

    public LoginUser getCurrentUser(){
        return currentUser.get();
    }

    public void setCurrentUser(LoginUser userDomain){
        currentUser.set(userDomain);
    }

}
