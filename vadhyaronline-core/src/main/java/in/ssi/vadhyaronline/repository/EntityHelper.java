package in.ssi.vadhyaronline.repository;

import in.ssi.vadhyaronline.constants.CommonConstants;
import in.ssi.vadhyaronline.entity.EventStatusEntity;
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

    public static EventStatusEntity getDraftEventStatus(){
        EventStatusEntity eventStatus = new EventStatusEntity();
        eventStatus.setEventStatusId(CommonConstants.EventStatusConstants.EVENT_STATUS_DRAFT_ID);
        eventStatus.setEventStatusName(CommonConstants.EventStatusConstants.EVENT_STATUS_DRAFT);
        return eventStatus;
    }

    public static EventStatusEntity getRequestedEventStatus(){
        EventStatusEntity eventStatus = new EventStatusEntity();
        eventStatus.setEventStatusId(CommonConstants.EventStatusConstants.EVENT_STATUS_REQUESTED_ID);
        eventStatus.setEventStatusName(CommonConstants.EventStatusConstants.EVENT_STATUS_REQUESTED);
        return eventStatus;
    }

    public static EventStatusEntity getAcceptedEventStatus(){
        EventStatusEntity eventStatus = new EventStatusEntity();
        eventStatus.setEventStatusId(CommonConstants.EventStatusConstants.EVENT_STATUS_ACCEPTED_ID);
        eventStatus.setEventStatusName(CommonConstants.EventStatusConstants.EVENT_STATUS_ACCEPTED);
        return eventStatus;
    }

    public static EventStatusEntity getCancelledEventStatus(){
        EventStatusEntity eventStatus = new EventStatusEntity();
        eventStatus.setEventStatusId(CommonConstants.EventStatusConstants.EVENT_STATUS_CANCELLED_ID);
        eventStatus.setEventStatusName(CommonConstants.EventStatusConstants.EVENT_STATUS_CANCELLED);
        return eventStatus;
    }

    public static EventStatusEntity getCompletedEventStatus(){
        EventStatusEntity eventStatus = new EventStatusEntity();
        eventStatus.setEventStatusId(CommonConstants.EventStatusConstants.EVENT_STATUS_COMPLETED_ID);
        eventStatus.setEventStatusName(CommonConstants.EventStatusConstants.EVENT_STATUS_COMPLETED);
        return eventStatus;
    }

}
