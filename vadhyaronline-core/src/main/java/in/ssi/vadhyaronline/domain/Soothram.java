package in.ssi.vadhyaronline.domain;

public class Soothram {

    private int soothramId;

    private String soothramName;

    private boolean approved;

    public Soothram() {

    }

    public Soothram(int soothramId, String soothramName, boolean approved) {
        this.soothramId = soothramId;
        this.soothramName = soothramName;
        this.approved = approved;
    }

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
}
