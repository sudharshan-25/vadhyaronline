package in.ssi.vadhyaronline.authentication;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface VOAuthenticated {
    /**
     * authentication needed
     * @return
     */
    boolean authenticate() default true;
}
