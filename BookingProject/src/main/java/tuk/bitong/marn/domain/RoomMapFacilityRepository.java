package tuk.bitong.marn.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface RoomMapFacilityRepository extends CrudRepository<RoomMapFacility, Long> {


    @Query(value = "select r from RoomMapFacility r where r.mapRoom =?1 ")
    public List<Room> findByRoomMapFacilityWithRoom(Room room);

}