package tuk.bitong.marn.domain;

import javax.persistence.*;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "roompicture",
        uniqueConstraints={@UniqueConstraint(columnNames={"roompicturehotel","roompicturepicturestore","roompictureroom"})})
public class RoomPicture {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="roompictureid")
    private Long roomPictureId;

    @ManyToOne
    @JoinColumn(name = "roompicturehotel")
    private Hotel roomPictureHotel;

    @ManyToOne
    @JoinColumn(name = "roompictureroom")
    private Room roomPictureRoom;

    @ManyToOne
    @JoinColumn(name = "roompicturepicturestore")
    private PictureStore roomPicturePictureStore;

    @Column(name ="enabled")
    private int enabled;

    public RoomPicture() {
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public PictureStore getRoomPicturePictureStore() {
        return roomPicturePictureStore;
    }

    public void setRoomPicturePictureStore(PictureStore roomPicturePictureStore) {
        this.roomPicturePictureStore = roomPicturePictureStore;
    }

    public Hotel getRoomPictureHotel() {
        return roomPictureHotel;
    }

    public void setRoomPictureHotel(Hotel roomPictureHotel) {
        this.roomPictureHotel = roomPictureHotel;
    }

    public Long getRoomPictureId() {
        return roomPictureId;
    }

    public void setRoomPictureId(Long roomPictureId) {
        this.roomPictureId = roomPictureId;
    }

    public Room getRoomPictureRoom() {
        return roomPictureRoom;
    }

    public void setRoomPictureRoom(Room roomPictureRoom) {
        this.roomPictureRoom = roomPictureRoom;
    }
}
