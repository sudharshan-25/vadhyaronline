package in.ssi.vadhyaronline.authentication;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface VOAccessRoles {
    /**
     * Access roles
     * @return
     */
    VOAccessRole[] accessRoles();


    public static final VOAccessRole[] ALL_ROLES = {VOAccessRole.ADMIN, VOAccessRole.VADHYAR, VOAccessRole.USER};

    public static final VOAccessRole[] NON_ADMIN_ROLES = {VOAccessRole.VADHYAR, VOAccessRole.USER};
}
