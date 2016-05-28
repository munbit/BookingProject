package tuk.bitong.marn.domain;

import javax.persistence.*;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "district")
public class District {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="districtid")
    private Long districtId;

    @Column(name="districtnamethai",nullable = false,unique = true)
    private String districtNameThai;

    @Column(name="districtnameenglish",nullable = false,unique = true)
    private String districtNameEnglish;

    @ManyToOne
    private Province districtProvince;

    @Column(name ="enabled")
    private int enabled;

    public District() {
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getDistrictNameThai() {
        return districtNameThai;
    }

    public void setDistrictNameThai(String districtNameThai) {
        this.districtNameThai = districtNameThai;
    }

    public String getDistrictNameEnglish() {
        return districtNameEnglish;
    }

    public void setDistrictNameEnglish(String districtNameEnglish) {
        this.districtNameEnglish = districtNameEnglish;
    }

    public Province getDistrictProvince() {
        return districtProvince;
    }

    public void setDistrictProvince(Province districtProvince) {
        this.districtProvince = districtProvince;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
