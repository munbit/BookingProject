package tuk.bitong.marn.domain;

import javax.persistence.*;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "facilitytype")
public class FacilityType {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="facilitytypeid")
    private Long facilityTypeId;

    @Column(name="facilitytypenamethai",nullable = false)
    private String facilityTypeNameThai;

    @Column(name="facilitytypenameenglish",nullable = false)
    private String facilityTypeNameEnglish;

    @Column(name ="enabled")
    private int enabled;

    public FacilityType() {
    }

    public Long getFacilityTypeId() {
        return facilityTypeId;
    }

    public void setFacilityTypeId(Long facilityTypeId) {
        this.facilityTypeId = facilityTypeId;
    }

    public String getFacilityTypeNameEnglish() {
        return facilityTypeNameEnglish;
    }

    public void setFacilityTypeNameEnglish(String facilityTypeNameEnglish) {
        this.facilityTypeNameEnglish = facilityTypeNameEnglish;
    }

    public String getFacilityTypeNameThai() {
        return facilityTypeNameThai;
    }

    public void setFacilityTypeNameThai(String facilityTypeNameThai) {
        this.facilityTypeNameThai = facilityTypeNameThai;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
