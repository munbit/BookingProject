package tuk.bitong.marn.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface DistrictRepository extends CrudRepository<District, Long> {
    public District findByDistrictNameThai(String districtNameThai);
    public District findByDistrictNameEnglish(String districtNameEnglish);

    @Query("select d from  District d where d.districtProvince=?1 ")
    public List<District> findByProvinceWithProvince(Province province);
}