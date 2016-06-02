package tuk.bitong.marn.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    @Query(value = "select r from Room r where r.roomHotel =?1 ")
    public List<Room> findByRoomWithHotel(Hotel hotel);
}