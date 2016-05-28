package tuk.bitong.marn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tuk.bitong.marn.domain.*;

import java.util.List;

/**
 * Created by muhai on 26/04/2559.
 */
@RestController
@RequestMapping(value = "rest/api")
public class RestAPIController {

    @Qualifier("countryRepository")
    @Autowired
    private CountryRepository countryRepository;

    @Qualifier("provinceRepository")
    @Autowired
    private ProvinceRepository provinceRepository;

    @Qualifier("districtRepository")
    @Autowired
    private DistrictRepository districtRepository;

    @Qualifier("localityRepository")
    @Autowired
    private LocalityRepository localityRepository;

    @RequestMapping(value = "/listProvinceByCountryCode", method = RequestMethod.GET)
    public ResponseEntity<List<Province>> listProvinceByCountry(@RequestParam("countryCode") Long countryCode) {

        Country country =countryRepository.findOne(countryCode);
        List<Province> provinces = provinceRepository.findByProvinceWithCountry(country);

        return new ResponseEntity<List<Province>>(provinces, HttpStatus.OK);
    }

    @RequestMapping(value = "/listDistrictByProvinceCode", method = RequestMethod.GET)
    public ResponseEntity<List<District>> listDistrictByProvince(@RequestParam("provinceCode") Long provinceCode) {

        Province province =provinceRepository.findOne(provinceCode);
        List<District> districts = districtRepository.findByProvinceWithProvince(province);

        return new ResponseEntity<List<District>>(districts, HttpStatus.OK);
    }

    @RequestMapping(value = "/listLocalityByDistrictCode", method = RequestMethod.GET)
    public ResponseEntity<List<Locality>> listLocalityByDistrict(@RequestParam("districtCode") Long districtCode) {

        District district =districtRepository.findOne(districtCode);
        List<Locality> districts = localityRepository.findByProvinceWithDistrict(district);

        return new ResponseEntity<List<Locality>>(districts, HttpStatus.OK);
    }
}
