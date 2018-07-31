package in.ssi.vadhyaronline.dao;

import in.ssi.vadhyaronline.constants.CommonConstants;
import in.ssi.vadhyaronline.entity.StatusMasterEntity;

public class EntityHelper {

    private EntityHelper() {
    }

    public static StatusMasterEntity getActiveStatus() {
        StatusMasterEntity activeStatus = new StatusMasterEntity();
        activeStatus.setStatusId(CommonConstants.StatusConstants.STATUS_ACTIVE_ID);
        activeStatus.setStatusName(CommonConstants.StatusConstants.STATUS_ACTIVE_VALUE);
        return activeStatus;
    }

    public static StatusMasterEntity getInactiveStatus() {
        StatusMasterEntity activeStatus = new StatusMasterEntity();
        activeStatus.setStatusId(CommonConstants.StatusConstants.STATUS_INACTIVE_ID);
        activeStatus.setStatusName(CommonConstants.StatusConstants.STATUS_INACTIVE_VALUE);
        return activeStatus;
    }

    public static StatusMasterEntity getLoggedInStatus() {
        StatusMasterEntity activeStatus = new StatusMasterEntity();
        activeStatus.setStatusId(CommonConstants.StatusConstants.STATUS_LOGGED_IN_ID);
        activeStatus.setStatusName(CommonConstants.StatusConstants.STATUS_LOGGED_IN_VALUE);
        return activeStatus;
    }

    public static StatusMasterEntity getLockedStatus() {
        StatusMasterEntity activeStatus = new StatusMasterEntity();
        activeStatus.setStatusId(CommonConstants.StatusConstants.STATUS_LOCKED_ID);
        activeStatus.setStatusName(CommonConstants.StatusConstants.STATUS_LOCKED_VALUE);
        return activeStatus;
    }
}
