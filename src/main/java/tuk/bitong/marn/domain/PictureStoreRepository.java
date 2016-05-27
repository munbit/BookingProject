package tuk.bitong.marn.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface PictureStoreRepository extends CrudRepository<PictureStore, Long> {

    @Query(value = "select p from  PictureStore p where p.pictureStoreHotel=?1 ")
    public List<PictureStore> findByPictureStoreWithHotel(Hotel hotel);


}