package in.ssi.vadhyar.web.authentication;

import in.ssi.vadhyar.web.constants.CommonConstants;
import in.ssi.vadhyar.web.domain.LoginUser;
import in.ssi.vadhyar.web.exception.AlreadyLoggedInException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Order(1)
public class AuthenticationAOP {

    private CacheManager cacheManager;

    private LoginUserContext loginUserContext;

    public AuthenticationAOP(CacheManager cacheManager, LoginUserContext loginUserContext) {
        this.cacheManager = cacheManager;
        this.loginUserContext = loginUserContext;
    }

    // @Pointcut("@annotation(VOAuthenticated) || ( execution(public * *(..)) && within(@VOAuthenticated *) )")
    public void interceptAuthenticate() {
    }

    // @Before("interceptAuthenticate()")
    public void handleAuthentication(JoinPoint joinPoint) {
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
            loginUserContext.setCurrentUser((LoginUser) cache.get(token).get());
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
