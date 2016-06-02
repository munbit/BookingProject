package tuk.bitong.marn.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface ProvinceRepository extends CrudRepository<Province, Long> {
    public Province findByProvinceNameThai(String provinceNameThai);
    public Province findByProvinceNameEnglish(String provinceNameEnglish);

    @Query("select p from  Province p where p.provinceCountry=?1 ")
    public List<Province> findByProvinceWithCountry(Country country);
}