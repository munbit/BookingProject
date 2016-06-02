package tuk.bitong.marn.domain;

import javax.persistence.*;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "picturestore")
public class PictureStore {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="picturestoreid")
    private Long pictureStoreId;

    @ManyToOne
    private Hotel pictureStoreHotel;

    @Column(name="picturestorepath",nullable = false)
    private String pictureStorePath;

    @Column(name ="enabled")
    private int enabled;

    public PictureStore() {
    }

    public Long getPictureStoreId() {
        return pictureStoreId;
    }

    public void setPictureStoreId(Long pictureStoreId) {
        this.pictureStoreId = pictureStoreId;
    }

    public Hotel getPictureStoreHotel() {
        return pictureStoreHotel;
    }

    public void setPictureStoreHotel(Hotel pictureStoreHotel) {
        this.pictureStoreHotel = pictureStoreHotel;
    }

    public String getPictureStorePath() {
        return pictureStorePath;
    }

    public void setPictureStorePath(String pictureStorePath) {
        this.pictureStorePath = pictureStorePath;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
