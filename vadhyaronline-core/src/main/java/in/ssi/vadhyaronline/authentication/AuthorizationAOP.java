package in.ssi.vadhyaronline.authentication;

import in.ssi.vadhyaronline.domain.LoginUser;
import in.ssi.vadhyaronline.exception.NoAccessException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(2)
public class AuthorizationAOP {

    private LoginUserContext loginUserContext;

    public AuthorizationAOP(LoginUserContext loginUserContext) {
        this.loginUserContext = loginUserContext;
    }

    // @Pointcut("@annotation(in.ssi.vadhyaronline.authentication.VOAccessRoles) || ( execution(public * *(..)) && within(@in.ssi.vadhyaronline.authentication.VOAccessRoles *) )")
    public void interceptAccessRoles() {
    }

    // @Before("interceptAccessRoles()")
    public void handleAccessRoles(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        VOAccessRoles roleAccess = signature.getMethod().getAnnotation(VOAccessRoles.class);
        if (roleAccess == null) {
            roleAccess = joinPoint.getTarget().getClass().getAnnotation(VOAccessRoles.class);
        }
        VOAccessRole[] voAccessRoles = roleAccess.accessRoles();
        for(VOAccessRole accessRole: voAccessRoles) {
            if(accessRole.equals(VOAccessRole.ANONYMOUS)){
                return;
            }
        }
        LoginUser userDomain = loginUserContext.getCurrentUser();
        String role = userDomain.getRole();
        if (Arrays.stream(voAccessRoles).noneMatch(voAccessRole -> voAccessRole.value.equalsIgnoreCase(role))) {
            throw new NoAccessException("Cannot access the resource");
        }
    }

}
