package tuk.bitong.marn.domain;

import javax.persistence.*;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "Locality")
public class Locality {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="localityid")
    private Long localityId;

    @Column(name="localitynamethai",nullable = false,unique = true)
    private String localityNameThai;

    @Column(name="localitynameenglish",nullable = false,unique = true)
    private String localityNameEnglish;

    @ManyToOne
    private District localityDistrict;

    @Column(name="localityzipcode",nullable = false)
    private String localityZipCode;

    @Column(name ="enabled")
    private int enabled;

    public Locality() {
    }

    public Long getLocalityId() {
        return localityId;
    }

    public void setLocalityId(Long localityId) {
        this.localityId = localityId;
    }

    public String getLocalityNameThai() {
        return localityNameThai;
    }

    public void setLocalityNameThai(String localityNameThai) {
        this.localityNameThai = localityNameThai;
    }

    public String getLocalityNameEnglish() {
        return localityNameEnglish;
    }

    public void setLocalityNameEnglish(String localityNameEnglish) {
        this.localityNameEnglish = localityNameEnglish;
    }

    public District getLocalityDistrict() {
        return localityDistrict;
    }

    public void setLocalityDistrict(District localityDistrict) {
        this.localityDistrict = localityDistrict;
    }

    public String getLocalityZipCode() {
        return localityZipCode;
    }

    public void setLocalityZipCode(String localityZipCode) {
        this.localityZipCode = localityZipCode;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
