package in.ssi.vadhyaronline.authentication;

import in.ssi.vadhyaronline.constants.CommonConstants;

public enum VOAccessRole {

    ANONYMOUS("Anonymous"),
    ADMIN(CommonConstants.RoleConstants.ADMIN_ROLE_NAME),
    VADHYAR(CommonConstants.RoleConstants.VADHYAR_ROLE_NAME),
    ASSISTANT(CommonConstants.RoleConstants.ASSISTANT_ROLE_NAME),
    USER(CommonConstants.RoleConstants.USER_ROLE_NAME);

    VOAccessRole(String value) {
        this.value = value;
    }

    String value;

    public String getValue() {
        return value;
    }

}
