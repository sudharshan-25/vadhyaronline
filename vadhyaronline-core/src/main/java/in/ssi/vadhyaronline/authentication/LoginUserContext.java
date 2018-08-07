package in.ssi.vadhyaronline.authentication;

import in.ssi.vadhyaronline.domain.UserDomain;
import org.springframework.stereotype.Service;

@Service
public class LoginUserContext {

    private ThreadLocal<UserDomain> currentUser = new InheritableThreadLocal<>();

    public UserDomain getCurrentUser(){
        return currentUser.get();
    }

    public void setCurrentUser(UserDomain userDomain){
        currentUser.set(userDomain);
    }

}
