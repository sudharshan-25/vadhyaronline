package in.ssi.vadhyaronline.entity;

import in.ssi.vadhyaronline.domain.AbstractResponse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "language_supported")
public class LanguageSupportedEntity implements AbstractEntity {

    @Id
    @Column(name = "lang_id")
    private int id;
    @Column(name = "lang_key", unique = true)
    private String key;
    @Column(name = "lang_value")
    private String value;


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

    public AbstractResponse toDomain() {
        return new AbstractResponse(this.id, this.key, this.value);
    }
}
