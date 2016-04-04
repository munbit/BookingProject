package tuk.bitong.marn.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userid")
    private Long userId;

    @Column(name = "username",nullable = false,unique = true)
    private String userName;

    @Column(name = "userfullname",nullable = false)
    private String userFullName;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name ="enabled")
    private int enabled;

    public User(){

    }

    public User(User user) {
        this.userId = user.userId;
        this.userName = user.userName;
        this.userFullName = user.userFullName;
        this.email = user.email;
        this.password = user.password;
        this.enabled=user.enabled;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}