package in.ssi.vadhyaronline.entity;

import in.ssi.vadhyaronline.domain.EventTypeVO;

import javax.persistence.*;

@Entity
@Table(name = "event_type")
public class EventTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_type_id")
    private int eventTypeId;

    @ManyToOne
    @JoinColumn(name = "event_category_id")
    private EventCategoryEntity eventCategory;

    @Column(name = "event_type_name")
    private String eventTypeName;

    @Column(name = "event_type_desc")
    private String eventTypeDescription;

    @Column(name = "approved")
    private boolean approved;

    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public EventCategoryEntity getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategoryEntity eventCategory) {
        this.eventCategory = eventCategory;
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

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public EventTypeVO toDomain() {
        return new EventTypeVO(eventTypeId, eventCategory.getCategoryId(), eventCategory.getCategoryName(),
                eventTypeName, eventTypeDescription, approved);
    }
}
