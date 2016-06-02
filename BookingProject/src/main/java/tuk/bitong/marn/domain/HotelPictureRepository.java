package tuk.bitong.marn.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface HotelPictureRepository extends CrudRepository<HotelPicture, Long> {

    @Query(value = "select h from HotelPicture h where h.hotelPictureHotel =?1 ")
    public List<HotelPicture> findByhotelPictureHotelWithHotel(Hotel hotel);

}