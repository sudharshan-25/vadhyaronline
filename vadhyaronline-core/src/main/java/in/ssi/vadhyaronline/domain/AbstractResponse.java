package in.ssi.vadhyaronline.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

public class AbstractResponse {

    @JsonProperty("id")
    private int id;

    @JsonProperty("key")
    private String key;

    @JsonProperty("value")
    private String value;

    public AbstractResponse() {

    }

    public AbstractResponse(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public AbstractResponse(int id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
