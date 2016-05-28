package tuk.bitong.marn.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
    public Country findByCountryNameThai (String countryNameThai);
    public Country findByCountryNameEnglish (String countryNameEnglish);

}