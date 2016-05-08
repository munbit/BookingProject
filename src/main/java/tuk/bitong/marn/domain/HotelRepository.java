package tuk.bitong.marn.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {
    public Hotel findByHotelNameThai(String hotelNameThai);
    public Hotel findByHotelNameEnglish(String hotelNameEnglish);
}