package tuk.bitong.marn.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface ContinentRepository extends CrudRepository<Continent, Long> {
    public Continent findByContinentNameThai(String continentNameThai);
    public Continent findByContinentNameEnglish(String continentNameEnglish);

}