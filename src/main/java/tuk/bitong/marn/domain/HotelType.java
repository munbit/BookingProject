package tuk.bitong.marn.domain;

import javax.persistence.*;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "holteltype")
public class HotelType {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="hoteltypeid")
    private Long hotelTypeId;

    @Column(name="hoteltypenamethai",nullable = false)
    private String hotelTypeNameThai;

    @Column(name="hoteltypenameenglish",nullable = false)
    private String hotelTypeNameEnglish;

    @Column(name ="enabled")
    private int enabled;

    public HotelType() {
    }

    public Long getHotelTypeId() {
        return hotelTypeId;
    }

    public void setHotelTypeId(Long hotelTypeId) {
        this.hotelTypeId = hotelTypeId;
    }

    public String getHotelTypeNameThai() {
        return hotelTypeNameThai;
    }

    public void setHotelTypeNameThai(String hotelTypeNameThai) {
        this.hotelTypeNameThai = hotelTypeNameThai;
    }

    public String getHotelTypeNameEnglish() {
        return hotelTypeNameEnglish;
    }

    public void setHotelTypeNameEnglish(String hotelTypeNameEnglish) {
        this.hotelTypeNameEnglish = hotelTypeNameEnglish;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
