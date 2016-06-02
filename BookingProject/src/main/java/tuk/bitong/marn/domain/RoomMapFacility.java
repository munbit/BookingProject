package tuk.bitong.marn.domain;

import javax.persistence.*;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "roommapfacility",uniqueConstraints={@UniqueConstraint(columnNames={"maproom","mapfacilitytype"})})
public class RoomMapFacility {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "roommapfacilityid")
    private Long roomMapFacilityId;

    @ManyToOne
    @JoinColumn(name = "maproom")
    private Room mapRoom;

    @ManyToOne
    @JoinColumn(name = "mapfacilitytype")
    private FacilityType mapFacilityType;

    @Column(name = "enabled")
    private int enabled;

    public RoomMapFacility() {
    }

    public Long getRoomMapFacilityId() {
        return roomMapFacilityId;
    }

    public void setRoomMapFacilityId(Long roomMapFacilityId) {
        this.roomMapFacilityId = roomMapFacilityId;
    }

    public Room getMapRoom() {
        return mapRoom;
    }

    public void setMapRoom(Room mapRoom) {
        this.mapRoom = mapRoom;
    }

    public FacilityType getMapFacilityType() {
        return mapFacilityType;
    }

    public void setMapFacilityType(FacilityType mapFacilityType) {
        this.mapFacilityType = mapFacilityType;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
