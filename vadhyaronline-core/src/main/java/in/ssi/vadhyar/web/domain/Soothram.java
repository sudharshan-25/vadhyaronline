package in.ssi.vadhyar.web.domain;

public class Soothram {

    public Soothram() {

    }

    public Soothram(int soothramId, String soothramName, boolean approved, String requestedBy, String approvedBy) {
        this.soothramId = soothramId;
        this.soothramName = soothramName;
        this.approved = approved;
        this.requestedBy = requestedBy;
        this.approvedBy = approvedBy;
    }

    private int soothramId;

    private String soothramName;

    private boolean approved;

    private String requestedBy;

    private String approvedBy;

    public int getSoothramId() {
        return soothramId;
    }

    public void setSoothramId(int soothramId) {
        this.soothramId = soothramId;
    }

    public String getSoothramName() {
        return soothramName;
    }

    public void setSoothramName(String soothramName) {
        this.soothramName = soothramName;
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
