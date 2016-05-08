package tuk.bitong.marn.domain;

import javax.persistence.*;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "roomtype")
public class RoomType {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="roomtypeid")
    private Long roomTypeId;

    @Column(name="roomtypenamethai",nullable = false)
    private String roomTypeNameThai;

    @Column(name="roomtypenameenglish",nullable = false)
    private String roomTypeNameEnglish;

    @Column(name ="enabled")
    private int enabled;

    public RoomType() {
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeNameThai() {
        return roomTypeNameThai;
    }

    public void setRoomTypeNameThai(String roomTypeNameThai) {
        this.roomTypeNameThai = roomTypeNameThai;
    }

    public String getRoomTypeNameEnglish() {
        return roomTypeNameEnglish;
    }

    public void setRoomTypeNameEnglish(String roomTypeNameEnglish) {
        this.roomTypeNameEnglish = roomTypeNameEnglish;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
