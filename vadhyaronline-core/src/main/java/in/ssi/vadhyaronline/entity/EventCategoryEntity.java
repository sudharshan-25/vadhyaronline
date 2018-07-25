package in.ssi.vadhyaronline.entity;

import in.ssi.vadhyaronline.domain.AbstractResponse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="event_category")
public class EventCategoryEntity implements AbstractEntity {

    @Id
    @Column(name="event_category_id")
    private int categoryId;

    @Column(name = "event_category_name")
    private String categoryName;

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

    @Override
    public AbstractResponse toDomain() {
        return new AbstractResponse(this.categoryId, this.categoryName);
    }
}
