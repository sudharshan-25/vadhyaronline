package in.ssi.vadhyar.web.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_master")
public class SimpleUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name")
    private String userName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SimpleUserEntity{");
        sb.append("userId=").append(userId);
        sb.append(", userName='").append(userName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static SimpleUserEntity of(int userId) {
        SimpleUserEntity userEntity = new SimpleUserEntity();
        userEntity.setUserId(userId);
        return userEntity;
    }
}
