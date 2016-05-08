package tuk.bitong.marn.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface HotelTypeRepository extends CrudRepository<HotelType, Long> {
    public HotelType findByHotelTypeNameThai(String hotelTypeNameThai);
    public HotelType findByHotelTypeNameEnglish(String hotelTypeNameEnglish);
}