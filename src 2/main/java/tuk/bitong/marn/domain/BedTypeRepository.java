package tuk.bitong.marn.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */


@Repository
public interface BedTypeRepository extends CrudRepository<BedType, Long> {
    public BedType findByBedTypeNameThai(String bedTypeNameThai);
    public BedType findByBedTypeNameEnglish(String bedTypeNameEnglish);
}