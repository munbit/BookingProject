package tuk.bitong.marn.domain;

import javax.persistence.*;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "bedtype")
public class BedType {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="bedtypeid")
    private Long bedTypeId;

    @Column(name="bedtypenamethai",nullable = false)
    private String bedTypeNameThai;

    @Column(name="bedtypenameenglish",nullable = false)
    private String bedTypeNameEnglish;

    @Column(name ="enabled")
    private int enabled;

    public BedType() {
    }

    public Long getBedTypeId() {
        return bedTypeId;
    }

    public void setBedTypeId(Long bedTypeId) {
        this.bedTypeId = bedTypeId;
    }

    public String getBedTypeNameThai() {
        return bedTypeNameThai;
    }

    public void setBedTypeNameThai(String bedTypeNameThai) {
        this.bedTypeNameThai = bedTypeNameThai;
    }

    public String getBedTypeNameEnglish() {
        return bedTypeNameEnglish;
    }

    public void setBedTypeNameEnglish(String bedTypeNameEnglish) {
        this.bedTypeNameEnglish = bedTypeNameEnglish;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
