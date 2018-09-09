package in.ssi.vadhyar.web.repository.helper;

import in.ssi.vadhyar.web.constants.CommonConstants;
import in.ssi.vadhyar.web.entity.StatusMasterEntity;

public class LoginStatusHelper {

    public static StatusMasterEntity getActiveStatus() {
        StatusMasterEntity statusMaster = new StatusMasterEntity();
        statusMaster.setStatusMasterId(CommonConstants.StatusConstants.STATUS_ACTIVE_ID);
        statusMaster.setStatusMasterName(CommonConstants.StatusConstants.STATUS_ACTIVE_VALUE);
        return statusMaster;
    }

    public static StatusMasterEntity getInActiveStatus() {
        StatusMasterEntity statusMaster = new StatusMasterEntity();
        statusMaster.setStatusMasterId(CommonConstants.StatusConstants.STATUS_INACTIVE_ID);
        statusMaster.setStatusMasterName(CommonConstants.StatusConstants.STATUS_INACTIVE_VALUE);
        return statusMaster;
    }

    public static StatusMasterEntity getLoggedInStatus() {
        StatusMasterEntity statusMaster = new StatusMasterEntity();
        statusMaster.setStatusMasterId(CommonConstants.StatusConstants.STATUS_LOGGED_IN_ID);
        statusMaster.setStatusMasterName(CommonConstants.StatusConstants.STATUS_LOGGED_IN_VALUE);
        return statusMaster;
    }

    public static StatusMasterEntity getLockedStatus() {
        StatusMasterEntity statusMaster = new StatusMasterEntity();
        statusMaster.setStatusMasterId(CommonConstants.StatusConstants.STATUS_LOCKED_ID);
        statusMaster.setStatusMasterName(CommonConstants.StatusConstants.STATUS_LOCKED_VALUE);
        return statusMaster;
    }

}
