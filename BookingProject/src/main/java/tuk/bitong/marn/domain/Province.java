package tuk.bitong.marn.domain;

import javax.persistence.*;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "province")
public class Province {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="provinceid")
    private Long provinceId;

    @Column(name="provincenamethai",nullable = false,unique = true)
    private String provinceNameThai;

    @Column(name="provincenameenglish",nullable = false,unique = true)
    private String provinceNameEnglish;

    @ManyToOne
    private Country provinceCountry;

    @ManyToOne
    private Zone provinceZone;

    @Column(name ="enabled")
    private int enabled;

    public Province() {
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceNameThai() {
        return provinceNameThai;
    }

    public void setProvinceNameThai(String provinceNameThai) {
        this.provinceNameThai = provinceNameThai;
    }

    public String getProvinceNameEnglish() {
        return provinceNameEnglish;
    }

    public void setProvinceNameEnglish(String provinceNameEnglish) {
        this.provinceNameEnglish = provinceNameEnglish;
    }

    public Country getProvinceCountry() {
        return provinceCountry;
    }

    public void setProvinceCountry(Country provinceCountry) {
        this.provinceCountry = provinceCountry;
    }

    public Zone getProvinceZone() {
        return provinceZone;
    }

    public void setProvinceZone(Zone provinceZone) {
        this.provinceZone = provinceZone;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
