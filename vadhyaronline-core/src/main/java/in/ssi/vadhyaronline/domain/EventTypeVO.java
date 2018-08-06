package in.ssi.vadhyaronline.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EventTypeVO {

    private int eventTypeId;

    private String eventTypeName;

    private String eventTypeDescription;

    private EventCategoryVO eventCategory;

    private boolean approved;

    public EventTypeVO() {

    }

    public EventTypeVO(int eventTypeId, int eventCategoryId, String eventCategoryName, String eventTypeName,
                       String eventTypeDescription, boolean approved) {
        this.eventTypeId = eventTypeId;
        this.eventTypeName = eventTypeName;
        this.eventTypeDescription = eventTypeDescription;
        this.eventCategory = new EventCategoryVO(eventCategoryId, eventCategoryName);
        this.setApproved(approved);
    }

    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    public String getEventTypeDescription() {
        return eventTypeDescription;
    }

    public void setEventTypeDescription(String eventTypeDescription) {
        this.eventTypeDescription = eventTypeDescription;
    }

    public EventCategoryVO getEventCategory() {
        return eventCategory;
    }

    @JsonIgnore
    public int getEventCategoryId() {
        return eventCategory.getEventCategoryId();
    }

    public void setEventCategory(EventCategoryVO eventCategory) {
        this.eventCategory = eventCategory;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    class EventCategoryVO {

        public EventCategoryVO() {

        }

        public EventCategoryVO(int eventCategoryId, String eventCategoryName) {
            this.eventCategoryId = eventCategoryId;
            this.eventCategoryName = eventCategoryName;
        }

        private int eventCategoryId;

        private String eventCategoryName;

        public int getEventCategoryId() {
            return eventCategoryId;
        }

        public void setEventCategoryId(int eventCategoryId) {
            this.eventCategoryId = eventCategoryId;
        }

        public String getEventCategoryName() {
            return eventCategoryName;
        }

        public void setEventCategoryName(String eventCategoryName) {
            this.eventCategoryName = eventCategoryName;
        }
    }
}
