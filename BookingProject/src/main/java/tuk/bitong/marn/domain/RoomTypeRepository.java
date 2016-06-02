package tuk.bitong.marn.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface RoomTypeRepository extends CrudRepository<RoomType, Long> {
    public RoomType findByRoomTypeNameThai(String roomTypeNameThai);
    public RoomType findByRoomTypeNameEnglish(String roomTypeNameEnglish);
}