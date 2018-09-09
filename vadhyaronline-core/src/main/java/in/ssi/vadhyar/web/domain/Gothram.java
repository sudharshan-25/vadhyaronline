package in.ssi.vadhyar.web.domain;

public class Gothram {

    private int gothramId;

    private String gothramName;

    private boolean approved;

    private String requestedBy;

    private String approvedBy;

    public Gothram(){

    }

    public Gothram(int gothramId, String gothramName, boolean approved, String requestedBy, String approvedBy) {
        this.gothramId = gothramId;
        this.gothramName = gothramName;
        this.approved = approved;
        this.requestedBy = requestedBy;
        this.approvedBy = approvedBy;
    }

    public int getGothramId() {
        return gothramId;
    }

    public void setGothramId(int gothramId) {
        this.gothramId = gothramId;
    }

    public String getGothramName() {
        return gothramName;
    }

    public void setGothramName(String gothramName) {
        this.gothramName = gothramName;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
}
