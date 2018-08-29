package in.ssi.vadhyar.web.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "event_type")
@NamedQuery(name = "EventTypeEntity.findOnlyApproved",
        query = "SELECT et FROM EventTypeEntity et WHERE et.approved = :approved AND et.eventCategory.approved = :approved")
public class EventTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_type_id")
    int eventTYpeId;

    @Column(name = "event_type_name", unique = true)
    String eventTypeName;

    @Column(name = "event_type_desc")
    String eventTypeDescription;

    @ManyToOne
    @JoinColumn(name = "event_category_id")
    EventCategoryEntity eventCategory;

    @Column(name = "approved")
    private boolean approved;

    @Column(name = "requested_by")
    private Integer requestedBy;

    @Column(name = "requested_on")
    private Timestamp requestedOn;

    @Column(name = "approved_by")
    private Integer approvedBy;

    @Column(name = "approved_on")
    private Timestamp approvedOn;

    public int getEventTYpeId() {
        return eventTYpeId;
    }

    public void setEventTYpeId(int eventTYpeId) {
        this.eventTYpeId = eventTYpeId;
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


    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Integer getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(Integer requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Timestamp getRequestedOn() {
        return requestedOn;
    }

    public void setRequestedOn(Timestamp requestedOn) {
        this.requestedOn = requestedOn;
    }

    public Integer getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Timestamp getApprovedOn() {
        return approvedOn;
    }

    public void setApprovedOn(Timestamp approvedOn) {
        this.approvedOn = approvedOn;
    }
}
