package in.ssi.vadhyaronline.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VadhyarResponse {

    @JsonProperty("DATA")
    private Object data;

    @JsonProperty("ERROR")
    private Object error;


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
