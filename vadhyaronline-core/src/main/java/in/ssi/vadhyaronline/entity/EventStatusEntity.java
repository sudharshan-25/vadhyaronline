package in.ssi.vadhyaronline.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event_status")
public class EventStatusEntity {

    @Id
    @Column(name = "event_status_id")
    private
    int eventStatusId;

    @Column(name = "event_status_name")
    private
    String eventStatusName;

    public int getEventStatusId() {
        return eventStatusId;
    }

    public void setEventStatusId(int eventStatusId) {
        this.eventStatusId = eventStatusId;
    }

    public String getEventStatusName() {
        return eventStatusName;
    }

    public void setEventStatusName(String eventStatusName) {
        this.eventStatusName = eventStatusName;
    }
}
