package in.ssi.vadhyaronline.authentication;

import in.ssi.vadhyaronline.constants.CommonConstants;
import in.ssi.vadhyaronline.domain.UserDomain;
import in.ssi.vadhyaronline.exception.AlreadyLoggedInException;
import in.ssi.vadhyaronline.exception.NoAccessException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class AuthenticationAOP {

    private CacheManager cacheManager;

    private LoginUserContext loginUserContext;

    public AuthenticationAOP(CacheManager cacheManager, LoginUserContext loginUserContext) {
        this.cacheManager = cacheManager;
        this.loginUserContext = loginUserContext;
    }

    @Pointcut("@annotation(in.ssi.vadhyaronline.authentication.VOAuthenticated) || ( execution(public * *(..)) && within(@in.ssi.vadhyaronline.authentication.VOAuthenticated *) )")
    public void interceptAuthenticate() {
    }

    @Before("interceptAuthenticate()")
    public void handleAuthentication(JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        VOAuthenticated authenticated = signature.getMethod().getAnnotation(VOAuthenticated.class);
        if (authenticated == null) {
            authenticated = joinPoint.getTarget().getClass().getAnnotation(VOAuthenticated.class);
        }
        if (authenticated.authenticate()) {
            HttpServletRequest request = getCurrentHttpRequest();
            String token = request.getHeader("X-Auth-Token");
            if (StringUtils.isEmpty(token)) {
                throw new AlreadyLoggedInException("User token not available");
            }
            Cache cache = cacheManager.getCache(CommonConstants.CacheConstants.LOGIN_USERS);
            if (cache.get(token) == null) {
                throw new AlreadyLoggedInException("User not authenticated");
            }
            loginUserContext.setCurrentUser((UserDomain) cache.get(token).get());
        }
    }

    @Pointcut("@annotation(in.ssi.vadhyaronline.authentication.VOAccessRoles) || ( execution(public * *(..)) && within(@in.ssi.vadhyaronline.authentication.VOAccessRoles *) )")
    public void interceptAccessRoles() {
    }

    @Before("interceptAccessRoles()")
    public void handleAccessRoles(JoinPoint joinPoint) throws Throwable {
        this.handleAuthentication(joinPoint);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        VOAccessRoles roleAccess = signature.getMethod().getAnnotation(VOAccessRoles.class);
        if (roleAccess == null) {
            roleAccess = joinPoint.getTarget().getClass().getAnnotation(VOAccessRoles.class);
        }
        VOAccessRole[] voAccessRoles = roleAccess.accessRoles();
        HttpServletRequest request = getCurrentHttpRequest();
        String token = request.getHeader("X-Auth-Token");
        UserDomain userDomain = loginUserContext.getCurrentUser();
        String role = userDomain.getRole();
        if (Arrays.stream(voAccessRoles).noneMatch(voAccessRole -> voAccessRole.value.equalsIgnoreCase(role))) {
            throw new NoAccessException("Cannot access the resource");
        }
    }

    private HttpServletRequest getCurrentHttpRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }
}