package tuk.bitong.marn.domain;

import javax.persistence.*;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "zone")
public class Zone {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="zoneid")
    private Long zoneId;

    @Column(name="zonenamethai",nullable = false,unique = true)
    private String zoneNameThai;

    @Column(name="zonenameenglish",nullable = false,unique = true)
    private String zoneNameEnglish;

    @Column(name ="enabled")
    private int enabled;

    public Zone() {
    }

    public String getZoneNameThai() {
        return zoneNameThai;
    }

    public void setZoneNameThai(String zoneNameThai) {
        this.zoneNameThai = zoneNameThai;
    }

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneNameEnglish() {
        return zoneNameEnglish;
    }

    public void setZoneNameEnglish(String zoneNameEnglish) {
        this.zoneNameEnglish = zoneNameEnglish;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
