package tuk.bitong.marn.domain;

import javax.persistence.*;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "country")
public class Country {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="countryid")
    private Long countryId;

    @Column(name="countrynamethai",nullable = false,unique = true)
    private String countryNameThai;

    @Column(name="countrynameenglish",nullable = false,unique = true)
    private String countryNameEnglish;

    @ManyToOne
    private Continent provinceContinent;

    @Column(name ="enabled")
    private int enabled;

    public Country() {
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountryNameThai() {
        return countryNameThai;
    }

    public void setCountryNameThai(String countryNameThai) {
        this.countryNameThai = countryNameThai;
    }

    public String getCountryNameEnglish() {
        return countryNameEnglish;
    }

    public void setCountryNameEnglish(String countryNameEnglish) {
        this.countryNameEnglish = countryNameEnglish;
    }

    public Continent getProvinceContinent() {
        return provinceContinent;
    }

    public void setProvinceContinent(Continent provinceContinent) {
        this.provinceContinent = provinceContinent;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
