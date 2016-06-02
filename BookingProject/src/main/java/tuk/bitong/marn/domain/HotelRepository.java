package tuk.bitong.marn.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {

    @Query(value = "select h from Hotel h where h.hotelNameThai LIKE  CONCAT('%',:name,'%')  OR h.hotelNameEnglish LIKE  CONCAT('%',:name,'%') ")
    public List<Hotel> findByAllName(@Param("name") String name);

}