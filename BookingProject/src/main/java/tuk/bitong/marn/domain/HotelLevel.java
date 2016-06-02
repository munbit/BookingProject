package tuk.bitong.marn.domain;

import javax.persistence.*;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "holtellevel")
public class HotelLevel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="holtellevelid")
    private Long hotelLevelId;

    @Column(name="holtellevelnamethai",nullable = false)
    private String hotelLevelNameThai;

    @Column(name="holtellevelnameenglish",nullable = false)
    private String hotelLevelNameEnglish;

    @Column(name="holtellevelval",nullable = false)
    private int hotelLevelVal;

    @Column(name ="enabled")
    private int enabled;

    public HotelLevel() {
    }

    public Long getHotelLevelId() {
        return hotelLevelId;
    }

    public void setHotelLevelId(Long hotelLevelId) {
        this.hotelLevelId = hotelLevelId;
    }

    public String getHotelLevelNameThai() {
        return hotelLevelNameThai;
    }

    public void setHotelLevelNameThai(String hotelLevelNameThai) {
        this.hotelLevelNameThai = hotelLevelNameThai;
    }

    public String getHotelLevelNameEnglish() {
        return hotelLevelNameEnglish;
    }

    public void setHotelLevelNameEnglish(String hotelLevelNameEnglish) {
        this.hotelLevelNameEnglish = hotelLevelNameEnglish;
    }

    public int getHotelLevelVal() {
        return hotelLevelVal;
    }

    public void setHotelLevelVal(int hotelLevelVal) {
        this.hotelLevelVal = hotelLevelVal;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
