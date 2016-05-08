package tuk.bitong.marn.domain;

import javax.persistence.*;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "continent")
public class Continent {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="continentid")
    private Long continentId;

    @Column(name="continentnamethai",nullable = false,unique = true)
    private String continentNameThai;

    @Column(name="continentnameenglish",nullable = false,unique = true)
    private String continentNameEnglish;

    @Column(name ="enabled")
    private int enabled;

    public Continent() {
    }

    public Long getContinentId() {
        return continentId;
    }

    public void setContinentId(Long continentId) {
        this.continentId = continentId;
    }

    public String getContinentNameThai() {
        return continentNameThai;
    }

    public void setContinentNameThai(String continentNameThai) {
        this.continentNameThai = continentNameThai;
    }

    public String getContinentNameEnglish() {
        return continentNameEnglish;
    }

    public void setContinentNameEnglish(String continentNameEnglish) {
        this.continentNameEnglish = continentNameEnglish;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
