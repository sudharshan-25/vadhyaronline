package in.ssi.vadhyar.web.entity;

import in.ssi.vadhyar.web.domain.EventType;

import javax.persistence.*;

@Entity
@Table(name = "event_type")
public class EventTypeEntity extends AbstractApprovalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_type_id")
    int eventTypeId;

    @Column(name = "event_type_name", unique = true)
    String eventTypeName;

    @Column(name = "event_type_desc")
    String eventTypeDescription;

    @ManyToOne
    @JoinColumn(name = "event_category_id")
    EventCategoryEntity eventCategory;

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

    public EventCategoryEntity getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategoryEntity eventCategory) {
        this.eventCategory = eventCategory;
    }

    public EventType toDomain() {
        EventType eventType = new EventType();
        eventType.setEventTypeId(this.eventTypeId);
        eventType.setEventTypeName(this.eventTypeName);
        eventType.setEventTypeDescription(this.eventTypeDescription);
        eventType.setApproved(isApproved());
        eventType.setApprovedBy(getApprovedBy() != null ? getApprovedBy().getUserName() : "");
        eventType.setRequestedBy(getRequestedBy() != null ? getRequestedBy().getUserName() : "");
        eventType.setEventTypeName(this.eventTypeName);
        eventType.setEventCategoryId(this.eventCategory.getEventCategoryId());
        eventType.setEventCategoryName(this.eventCategory.getEventCategoryName());
        return eventType;
    }

}
