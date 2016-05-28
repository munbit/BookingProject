package tuk.bitong.marn.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface FacilityTypeRepository extends CrudRepository<FacilityType, Long> {
    public FacilityType findByFacilityTypeNameThai(String facilityTypeNameThai);
    public FacilityType findByFacilityTypeNameEnglish(String facilityTypeNameEnglish);
}