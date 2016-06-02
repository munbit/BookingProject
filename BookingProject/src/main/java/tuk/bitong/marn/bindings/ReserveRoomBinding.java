package tuk.bitong.marn.bindings;

import com.sun.tools.javac.util.List;
import tuk.bitong.marn.domain.Room;

/**
 * Created by MUHAIMAN-HENG on 5/24/2016 AD.
 */
public class ReserveRoomBinding extends Room {

    private int reserveRoomQuantity;

    public ReserveRoomBinding() {
    }

    public ReserveRoomBinding(Room room){
        this.roomId = room.getRoomId();
        this.roomHotel = room.getRoomHotel();
        this.roomBedType = room.getRoomBedType();
        this.roomRoomType = room.getRoomRoomType();
        this.roomQuantity = room.getRoomQuantity();
        this.roomMaxPerson = room.getRoomMaxPerson();
        this.roomNormalPrice = room.getRoomNormalPrice();
        this.roomExtraPrice = room.getRoomExtraPrice();
        this.roomRemarkThai = room.getRoomRemarkThai();
        this.roomCoditionThai = room.getRoomCoditionThai();
        this.roomRemarkEnglish = room.getRoomRemarkEnglish();
        this.roomCoditionEnglish = room.getRoomCoditionEnglish();
        this.enabled = room.getEnabled();
        this.reserveRoomQuantity =0;
    }

    public int getReserveRoomQuantity() {
        return reserveRoomQuantity;
    }

    public void setReserveRoomQuantity(int reserveRoomQuantity) {
        this.reserveRoomQuantity = reserveRoomQuantity;
    }
}
