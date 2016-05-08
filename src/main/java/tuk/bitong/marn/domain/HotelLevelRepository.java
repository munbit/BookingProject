package tuk.bitong.marn.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface HotelLevelRepository extends CrudRepository<HotelLevel, Long> {
    public HotelLevel findByHotelLevelNameThai(String hotelLevelNameThai);
    public HotelLevel findByHotelLevelNameEnglish(String hotelLevelNameEnglish);
}