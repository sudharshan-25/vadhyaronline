package in.ssi.vadhyaronline.domain;

public class EventCategory {

    private int categoryId;

    private String eventCategoryName;

    private boolean approved;

    public EventCategory(){
    }

    public EventCategory(int categoryId, String eventCategoryName, boolean approved) {
        this();
        this.categoryId = categoryId;
        this.eventCategoryName = eventCategoryName;
        this.approved = approved;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getEventCategoryName() {
        return eventCategoryName;
    }

    public void setEventCategoryName(String eventCategoryName) {
        this.eventCategoryName = eventCategoryName;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
