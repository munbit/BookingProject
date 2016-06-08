package tuk.bitong.marn.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "bookingdetail")
public class BookingDetail {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="bookingdetailid")
    private Long bookingDetailId;

    @ManyToOne
    @JoinColumn(name = "bookingdetailbookingmaster")
    protected BookingMaster bookingDetailBookingMaster;


    @ManyToOne
    @JoinColumn(name = "bookingdetailroom")
    protected Room bookingDetailRoom;

    @Column(name ="bookingdetailquantity")
    private int bookingdetailQuantity;

    @Column(name ="enabled")
    private int enabled;

    public BookingDetail() {
    }

    public Long getBookingDetailId() {
        return bookingDetailId;
    }

    public void setBookingDetailId(Long bookingDetailId) {
        this.bookingDetailId = bookingDetailId;
    }

    public BookingMaster getBookingDetailBookingMaster() {
        return bookingDetailBookingMaster;
    }

    public void setBookingDetailBookingMaster(BookingMaster bookingDetailBookingMaster) {
        this.bookingDetailBookingMaster = bookingDetailBookingMaster;
    }

    public Room getBookingDetailRoom() {
        return bookingDetailRoom;
    }

    public void setBookingDetailRoom(Room bookingDetailRoom) {
        this.bookingDetailRoom = bookingDetailRoom;
    }

    public int getBookingdetailQuantity() {
        return bookingdetailQuantity;
    }

    public void setBookingdetailQuantity(int bookingdetailQuantity) {
        this.bookingdetailQuantity = bookingdetailQuantity;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
