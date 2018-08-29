package in.ssi.vadhyar.web.entity;

import in.ssi.vadhyar.web.domain.EventCategory;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "event_category")
@NamedQuery(name = "findByApproved", query = " SELECT cat FROM EventCategoryEntity cat WHERE cat.approved = :approved ")
public class EventCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_category_id")
    private int eventCategoryId;

    @Column(name = "event_category_name")
    private String eventCategoryName;

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
        eventCategory.setApprovedBy("" + getApprovedBy());
        eventCategory.setRequestedBy("" + getRequestedBy());
        return eventCategory;
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
