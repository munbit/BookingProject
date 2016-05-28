package tuk.bitong.marn.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface RoomPictureRepository extends CrudRepository<RoomPicture, Long> {

    @Query(value = "select r from RoomPicture r where r.roomPictureHotel =?1 ")
    public List<RoomPicture> findByroomPictureHotelWithHotel(Hotel hotel);

}