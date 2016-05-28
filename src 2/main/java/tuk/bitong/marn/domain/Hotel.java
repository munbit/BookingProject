package tuk.bitong.marn.domain;

import javax.persistence.*;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "hotel")
public class Hotel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="hotelid")
    private Long hotelId;

    @Column(name="hotelnamethai",nullable = false)
    private String hotelNameThai;

    @Column(name="hotelnameenglish",nullable = false)
    private String hotelNameEnglish;

    @Lob
    @Column(name="hoteldescriptionthai",nullable = false)
    private String hotelDescriptionThai;

    @Lob
    @Column(name="hoteldescriptionenglish",nullable = false)
    private String hotelDescriptionEnglish;

    @Column(name="hoteladdressthai",nullable = false)
    private String hotelAddressThai;

    @Column(name="hoteladdressenglish",nullable = false)
    private String hotelAddressEnglish;

    @ManyToOne
    private Locality hotelLocality;

    @ManyToOne
    private HotelLevel hotelHotelLevel;

    @ManyToOne
    private HotelType hotelHotelType;

    @Column(name="hotelimageurl")
    private String hotelImageUrl;

    @Column(name ="enabled")
    private int enabled;

    public Hotel() {
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelNameThai() {
        return hotelNameThai;
    }

    public void setHotelNameThai(String hotelNameThai) {
        this.hotelNameThai = hotelNameThai;
    }

    public String getHotelNameEnglish() {
        return hotelNameEnglish;
    }

    public void setHotelNameEnglish(String hotelNameEnglish) {
        this.hotelNameEnglish = hotelNameEnglish;
    }

    public String getHotelDescriptionThai() {
        return hotelDescriptionThai;
    }

    public void setHotelDescriptionThai(String hotelDescriptionThai) {
        this.hotelDescriptionThai = hotelDescriptionThai;
    }

    public String getHotelDescriptionEnglish() {
        return hotelDescriptionEnglish;
    }

    public void setHotelDescriptionEnglish(String hotelDescriptionEnglish) {
        this.hotelDescriptionEnglish = hotelDescriptionEnglish;
    }

    public String getHotelAddressThai() {
        return hotelAddressThai;
    }

    public void setHotelAddressThai(String hotelAddressThai) {
        this.hotelAddressThai = hotelAddressThai;
    }

    public String getHotelAddressEnglish() {
        return hotelAddressEnglish;
    }

    public void setHotelAddressEnglish(String hotelAddressEnglish) {
        this.hotelAddressEnglish = hotelAddressEnglish;
    }

    public Locality getHotelLocality() {
        return hotelLocality;
    }

    public void setHotelLocality(Locality hotelLocality) {
        this.hotelLocality = hotelLocality;
    }

    public HotelLevel getHotelHotelLevel() {
        return hotelHotelLevel;
    }

    public void setHotelHotelLevel(HotelLevel hotelHotelLevel) {
        this.hotelHotelLevel = hotelHotelLevel;
    }

    public HotelType getHotelHotelType() {
        return hotelHotelType;
    }

    public void setHotelHotelType(HotelType hotelHotelType) {
        this.hotelHotelType = hotelHotelType;
    }

    public String getHotelImageUrl() {
        return hotelImageUrl;
    }

    public void setHotelImageUrl(String hotelImageUrl) {
        this.hotelImageUrl = hotelImageUrl;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
