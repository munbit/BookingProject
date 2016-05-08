package tuk.bitong.marn.domain;

import javax.persistence.*;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "bettype")
public class BedType {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="bettypeid")
    private Long betTypeId;

    @Column(name="bettypenamethai",nullable = false)
    private String betTypeNameThai;

    @Column(name="bettypenameenglish",nullable = false)
    private String betTypeNameEnglish;

    @Column(name ="enabled")
    private int enabled;

    public BedType() {
    }

    public Long getBetTypeId() {
        return betTypeId;
    }

    public void setBetTypeId(Long betTypeId) {
        this.betTypeId = betTypeId;
    }

    public String getBetTypeNameThai() {
        return betTypeNameThai;
    }

    public void setBetTypeNameThai(String betTypeNameThai) {
        this.betTypeNameThai = betTypeNameThai;
    }

    public String getBetTypeNameEnglish() {
        return betTypeNameEnglish;
    }

    public void setBetTypeNameEnglish(String betTypeNameEnglish) {
        this.betTypeNameEnglish = betTypeNameEnglish;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
