package tuk.bitong.marn.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface ZoneRepository extends CrudRepository<Zone, Long> {
    public Zone findByZoneNameThai(String zonenamethai);
    public Zone findByZoneNameEnglish(String zonenameenglish);
}