package tuk.bitong.marn.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Entity
@Table(name = "user_roles",uniqueConstraints={@UniqueConstraint(columnNames = {"userid" , "role"})})
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_role_id")
    private Long userRoleId;

    @Column(name = "userid",nullable = false)
    private Long userId;

    @Column(name = "role",nullable = false)
    private String role;

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
