package tuk.bitong.marn.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "bookingmaster")
public class BookingMaster {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="bookingmasterid")
    private Long bookingMasterId;

    @Column(name ="bookingmasterdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingMasterDate;

    @Column(name ="bookingmastertotalamount")
    private Double bookingMasterTotalAmount;

    @ManyToOne
    @JoinColumn(name = "bookingmasterhotel")
    protected Hotel bookingMasterHotel;

    @ManyToOne
    @JoinColumn(name = "bookingmasteruser")
    protected User bookingMasterUser;

    @Column(name ="bookingmasterstartdate")
    private Date bookingMasterStartDate;

    @Column(name ="bookingmastermasterenddate")
    private Date bookingMasterEndDate;

    @Column(name ="bookingmasterpaydate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingMasterPayDate;

    @Column(name ="bookingmasterpaystatus")
    private String bookingMasterPayStatus;

    @Column(name ="bookingmasterreturnstatus")
    private String bookingMasterReturnStatus;

    @Column(name ="bookingmasterpaymethod")
    private String bookingMasterPayMethod;

    @Column(name ="bookingmasterpaypaltransactionnumber")
    private String bookingMasterPaypalTransactionNumber;

    @Column(name ="enabled")
    private int enabled;

    public BookingMaster() {
    }

    public Long getBookingMasterId() {
        return bookingMasterId;
    }

    public void setBookingMasterId(Long bookingMasterId) {
        this.bookingMasterId = bookingMasterId;
    }

    public Date getBookingMasterDate() {
        return bookingMasterDate;
    }

    public void setBookingMasterDate(Date bookingMasterDate) {
        this.bookingMasterDate = bookingMasterDate;
    }

    public Double getBookingMasterTotalAmount() {
        return bookingMasterTotalAmount;
    }

    public void setBookingMasterTotalAmount(Double bookingMasterTotalAmount) {
        this.bookingMasterTotalAmount = bookingMasterTotalAmount;
    }

    public Hotel getBookingMasterHotel() {
        return bookingMasterHotel;
    }

    public void setBookingMasterHotel(Hotel bookingMasterHotel) {
        this.bookingMasterHotel = bookingMasterHotel;
    }

    public User getBookingMasterUser() {
        return bookingMasterUser;
    }

    public void setBookingMasterUser(User bookingMasterUser) {
        this.bookingMasterUser = bookingMasterUser;
    }

    public Date getBookingMasterStartDate() {
        return bookingMasterStartDate;
    }

    public void setBookingMasterStartDate(Date bookingMasterStartDate) {
        this.bookingMasterStartDate = bookingMasterStartDate;
    }

    public Date getBookingMasterEndDate() {
        return bookingMasterEndDate;
    }

    public void setBookingMasterEndDate(Date bookingMasterEndDate) {
        this.bookingMasterEndDate = bookingMasterEndDate;
    }

    public Date getBookingMasterPayDate() {
        return bookingMasterPayDate;
    }

    public void setBookingMasterPayDate(Date bookingMasterPayDate) {
        this.bookingMasterPayDate = bookingMasterPayDate;
    }

    public String getBookingMasterPayStatus() {
        return bookingMasterPayStatus;
    }

    public void setBookingMasterPayStatus(String bookingMasterPayStatus) {
        this.bookingMasterPayStatus = bookingMasterPayStatus;
    }

    public String getBookingMasterReturnStatus() {
        return bookingMasterReturnStatus;
    }

    public void setBookingMasterReturnStatus(String bookingMasterReturnStatus) {
        this.bookingMasterReturnStatus = bookingMasterReturnStatus;
    }

    public String getBookingMasterPayMethod() {
        return bookingMasterPayMethod;
    }

    public void setBookingMasterPayMethod(String bookingMasterPayMethod) {
        this.bookingMasterPayMethod = bookingMasterPayMethod;
    }

    public String getBookingMasterPaypalTransactionNumber() {
        return bookingMasterPaypalTransactionNumber;
    }

    public void setBookingMasterPaypalTransactionNumber(String bookingMasterPaypalTransactionNumber) {
        this.bookingMasterPaypalTransactionNumber = bookingMasterPaypalTransactionNumber;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
