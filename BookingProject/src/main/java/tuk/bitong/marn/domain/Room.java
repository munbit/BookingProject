package tuk.bitong.marn.domain;

import javax.persistence.*;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "room")
public class Room {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="roomid")
    protected Long roomId;

    @ManyToOne
    protected Hotel roomHotel;

    @ManyToOne
    protected BedType roomBedType;

    @ManyToOne
    protected RoomType roomRoomType;

    @Column(name="roomquantity",nullable = false)
    protected int roomQuantity;

    @Column(name="roommaxperson",nullable = false)
    protected int roomMaxPerson;

    @Column(name="roomnormalprice",nullable = false, columnDefinition="Decimal(10,2) default '0.00'")
    protected Double roomNormalPrice;

    @Column(name="roomextraprice",nullable = false, columnDefinition="Decimal(10,2) default '0.00'")
    protected Double roomExtraPrice;

    @Column(name="roomremarkthai")
    protected String roomRemarkThai;

    @Column(name="roomcoditionthai")
    protected String roomCoditionThai;

    @Column(name="roomremarkenglish")
    protected String roomRemarkEnglish;

    @Column(name="roomcoditionenglish")
    protected String roomCoditionEnglish;

    @Column(name ="enabled")
    protected int enabled;

    public Room() {
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Hotel getRoomHotel() {
        return roomHotel;
    }

    public void setRoomHotel(Hotel roomHotel) {
        this.roomHotel = roomHotel;
    }

    public BedType getRoomBedType() {
        return roomBedType;
    }

    public void setRoomBedType(BedType roomBedType) {
        this.roomBedType = roomBedType;
    }

    public RoomType getRoomRoomType() {
        return roomRoomType;
    }

    public void setRoomRoomType(RoomType roomRoomType) {
        this.roomRoomType = roomRoomType;
    }

    public int getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
    }

    public int getRoomMaxPerson() {
        return roomMaxPerson;
    }

    public void setRoomMaxPerson(int roomMaxPerson) {
        this.roomMaxPerson = roomMaxPerson;
    }

    public Double getRoomNormalPrice() {
        return roomNormalPrice;
    }

    public void setRoomNormalPrice(Double roomNormalPrice) {
        this.roomNormalPrice = roomNormalPrice;
    }

    public Double getRoomExtraPrice() {
        return roomExtraPrice;
    }

    public void setRoomExtraPrice(Double roomExtraPrice) {
        this.roomExtraPrice = roomExtraPrice;
    }

    public String getRoomRemarkThai() {
        return roomRemarkThai;
    }

    public void setRoomRemarkThai(String roomRemarkThai) {
        this.roomRemarkThai = roomRemarkThai;
    }

    public String getRoomCoditionThai() {
        return roomCoditionThai;
    }

    public void setRoomCoditionThai(String roomCoditionThai) {
        this.roomCoditionThai = roomCoditionThai;
    }

    public String getRoomRemarkEnglish() {
        return roomRemarkEnglish;
    }

    public void setRoomRemarkEnglish(String roomRemarkEnglish) {
        this.roomRemarkEnglish = roomRemarkEnglish;
    }

    public String getRoomCoditionEnglish() {
        return roomCoditionEnglish;
    }

    public void setRoomCoditionEnglish(String roomCoditionEnglish) {
        this.roomCoditionEnglish = roomCoditionEnglish;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
