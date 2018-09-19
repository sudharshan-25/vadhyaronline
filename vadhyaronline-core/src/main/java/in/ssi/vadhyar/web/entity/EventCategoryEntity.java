package in.ssi.vadhyar.web.entity;

import in.ssi.vadhyar.web.domain.EventCategory;

import javax.persistence.*;

@Entity
@Table(name = "event_category")
public class EventCategoryEntity extends AbstractApprovalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_category_id")
    private int eventCategoryId;

    @Column(name = "event_category_name")
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

    public EventCategory toDomain() {
        EventCategory eventCategory = new EventCategory();
        eventCategory.setEventCategoryId(eventCategoryId);
        eventCategory.setEventCategoryName(eventCategoryName);
        eventCategory.setApproved(isApproved());
        eventCategory.setApprovedBy(getApprovedBy() != null ? getApprovedBy().getUserName() : "");
        eventCategory.setRequestedBy(getRequestedBy() != null ? getRequestedBy().getUserName() : "");
        return eventCategory;
    }

}
