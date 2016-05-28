package tuk.bitong.marn.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface BookingMasterRepository extends CrudRepository<BookingMaster, Long> {

    @Query(value = "select b from BookingMaster b where b.bookingMasterUser =?1 ")
    public List<BookingMaster> findByBookingMasterWithUser(User user);

}