package in.ssi.vadhyaronline.entity;

import in.ssi.vadhyaronline.domain.UserAddress;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user_address")
public class UserAddressEntity {

    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name = "property", value = "userMaster"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "user_id")
    private int userId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private UserMasterEntity userMaster;

    @Column(name = "address_flat_number")
    private String flatNumber;

    @Column(name = "address_street_name")
    private String streetName;

    @Column(name = "address_city")
    private String city;

    @Column(name = "address_state")
    private String state;

    @Column(name = "address_zipcode")
    private String zipCode;

    public UserMasterEntity getUserMaster() {
        return userMaster;
    }

    public void setUserMaster(UserMasterEntity userMaster) {
        this.userMaster = userMaster;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public UserAddress toDomain() {
        return new UserAddress(this.flatNumber, this.streetName, this.city, this.state, this.zipCode);
    }
}
