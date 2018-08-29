package in.ssi.vadhyar.web.domain;

import java.util.Objects;

public class DropDownChoice {

    private int key;

    private String value;

    private int criteriaId;

    private String criteriaValue;

    public DropDownChoice() {

    }

    public DropDownChoice(int key, String value) {
        this();
        this.key = key;
        this.value = value;
    }

    public DropDownChoice(int key, String value, int criteriaId, String criteriaValue) {
        this();
        this.key = key;
        this.value = value;
        this.criteriaId = criteriaId;
        this.criteriaValue = criteriaValue;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(int criteriaId) {
        this.criteriaId = criteriaId;
    }

    public String getCriteriaValue() {
        return criteriaValue;
    }

    public void setCriteriaValue(String criteriaValue) {
        this.criteriaValue = criteriaValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DropDownChoice that = (DropDownChoice) o;
        return key == that.key &&
                criteriaId == that.criteriaId &&
                Objects.equals(value, that.value) &&
                Objects.equals(criteriaValue, that.criteriaValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value, criteriaId, criteriaValue);
    }
}
