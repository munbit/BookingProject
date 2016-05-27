package tuk.bitong.marn.domain;

import javax.persistence.*;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "hotelpicture",uniqueConstraints={@UniqueConstraint(columnNames={"hotelpicturehotel","hotelpicturepicturestore"})})
public class HotelPicture {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="hotelpictureid")
    private Long hotelPictureId;

    @ManyToOne
    @JoinColumn(name = "hotelpicturehotel")
    private Hotel hotelPictureHotel;

    @ManyToOne
    @JoinColumn(name = "hotelpicturepicturestore")
    private PictureStore hotelPicturePictureStore;

    @Column(name ="enabled")
    private int enabled;

    public HotelPicture() {
    }

    public Long getHotelPictureId() {
        return hotelPictureId;
    }

    public void setHotelPictureId(Long hotelPictureId) {
        this.hotelPictureId = hotelPictureId;
    }

    public Hotel getHotelPictureHotel() {
        return hotelPictureHotel;
    }

    public void setHotelPictureHotel(Hotel hotelPictureHotel) {
        this.hotelPictureHotel = hotelPictureHotel;
    }

    public PictureStore getHotelPicturePictureStore() {
        return hotelPicturePictureStore;
    }

    public void setHotelPicturePictureStore(PictureStore hotelPicturePictureStore) {
        this.hotelPicturePictureStore = hotelPicturePictureStore;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
