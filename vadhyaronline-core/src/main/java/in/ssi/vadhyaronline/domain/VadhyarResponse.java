package in.ssi.vadhyaronline.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VadhyarResponse {

    @JsonProperty("DATA")
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
