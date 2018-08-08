package in.ssi.vadhyaronline.domain;

public class Gotharam {

    private int gothramId;

    private String gothramName;

    private boolean approved;

    public Gotharam(){
    }

    public Gotharam(int gothramId, String gothramName, boolean approved){
        this.gothramId = gothramId;
        this.gothramName = gothramName;
        this.approved = approved;
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
}
