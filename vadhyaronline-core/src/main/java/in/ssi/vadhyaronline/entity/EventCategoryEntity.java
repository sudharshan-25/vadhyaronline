package in.ssi.vadhyaronline.entity;

import in.ssi.vadhyaronline.domain.EventCategory;

import javax.persistence.*;

@Entity
@Table(name="event_category")
public class EventCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_category_id")
    private int categoryId;

    @Column(name = "event_category_name")
    private String categoryName;

    @Column(name = "approved")
    private boolean approved;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public EventCategory toDomain() {
        return new EventCategory(this.categoryId, this.categoryName, this.approved);
    }

}
