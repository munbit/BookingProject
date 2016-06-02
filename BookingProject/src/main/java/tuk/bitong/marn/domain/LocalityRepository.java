package tuk.bitong.marn.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface LocalityRepository extends CrudRepository<Locality, Long> {
    public Locality findByLocalityNameThai(String localityNameThai);
    public Locality findByLocalityNameEnglish(String localityNameEnglish);

    @Query("select l from  Locality l where l.localityDistrict=?1 ")
    public List<Locality> findByProvinceWithDistrict(District district);
}