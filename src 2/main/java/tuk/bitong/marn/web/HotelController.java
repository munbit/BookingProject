package tuk.bitong.marn.web;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;
import sun.awt.AppContext;
import tuk.bitong.marn.Application;
import tuk.bitong.marn.domain.*;

import java.applet.AppletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by muhai on 14/03/2559.
 */
@Controller
@RequestMapping(value = "admin/hotel")
public class HotelController {

    Context context = new Context();

    @Qualifier("hotelRepository")
    @Autowired
    private HotelRepository hotelRepository;

    @Qualifier("hotelLevelRepository")
    @Autowired
    private HotelLevelRepository hotelLevelRepository;

    @Qualifier("hotelTypeRepository")
    @Autowired
    private HotelTypeRepository hotelTypeRepository;

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

    @Autowired
    private ResourceLoader resourceLoader;

    public static String HOTEL_MASTER_UPLOAD = "/hotel_master";
    //public static String CLASSPATH_UPLOAD_DIR ="classpath:static/upload-dir";

    public HotelController() {
        context.setVariable("hotelList", null);
        context.setVariable("hotel", new Hotel());
        context.setVariable("hotelTypeList", null);
        context.setVariable("hotelType", new HotelType());
        context.setVariable("hotelLevelList", null);
        context.setVariable("hotelLevel", new HotelLevel());

        context.setVariable("countryList", null);
        context.setVariable("country", new Country());

        context.setVariable("provinceList", null);
        context.setVariable("province", new Province());

        context.setVariable("districtList", null);
        context.setVariable("district", new District());

        context.setVariable("localityList", null);
        context.setVariable("locality", new Locality());

    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    String hotelListPage(Model model) {
        model.addAttribute("hotelList", hotelRepository.findAll());
        Resource resource = resourceLoader.getResource(Application.CLASSPATH_UPLOAD_DIR);

        File file = null;

        try {
            //System.out.println(resource.getFile().getPath() + HOTEL_MASTER_UPLOAD);
            file = new File(resource.getFile().getPath() + HOTEL_MASTER_UPLOAD);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        return "hotels/hotel_list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    String hotelAddPage(Model model) {
        model.addAttribute("hotel", new Hotel());
        model.addAttribute("countryList", countryRepository.findAll());
        model.addAttribute("hotelTypeList", hotelTypeRepository.findAll());
        model.addAttribute("hotelLevelList", hotelLevelRepository.findAll());
        return "hotels/hotel_add";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    String hotelAddSave(Model model,
                        @ModelAttribute("hotel") Hotel hotel,
                        @RequestParam("uploadFileHotel") MultipartFile uploadFileHotel,
                        final RedirectAttributes redirectAttributes) {
        Resource resource = resourceLoader.getResource(Application.CLASSPATH_UPLOAD_DIR);
        try {
            Hotel h = hotelRepository.save(hotel);
            String fileName ="";
            if (!uploadFileHotel.isEmpty()) {
                try {
                    fileName = resource.getFile().getPath() + HOTEL_MASTER_UPLOAD + "/" + h.getHotelId() + "." + uploadFileHotel.getContentType().split("/")[1];
                    byte[] bytes = uploadFileHotel.getBytes();
                    BufferedOutputStream buffStream =
                            new BufferedOutputStream(new FileOutputStream(new File(fileName)));
                    buffStream.write(bytes);
                    buffStream.close();
                    h.setHotelImageUrl(HOTEL_MASTER_UPLOAD + "/" + h.getHotelId() + "." + uploadFileHotel.getContentType().split("/")[1]);
                    hotelRepository.save(h);
                    redirectAttributes.addFlashAttribute("statusSave", " You have successfully uploaded " + fileName);
                } catch (Exception e) {
                    model.addAttribute("hotel", hotel);
                    model.addAttribute("statusSave", "You failed to upload " + fileName + ": " + e.getMessage());
                    redirectAttributes.addFlashAttribute("statusSave", "You failed to upload " + fileName + ": " + e.getMessage());
                    return "redirect:/admin/hotel/";
                }
            } else {
                model.addAttribute("hotel", hotel);
                model.addAttribute("statusSave", "success");
                redirectAttributes.addFlashAttribute("statusSave", "success");
                return "redirect:/admin/hotel/";
            }

        } catch (Exception ex) {
            model.addAttribute("hotel", hotel);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "hotels/hotel_add";
        }
        return "redirect:/admin/hotel/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{hotelId}", method = RequestMethod.GET)
    String hotelEditPage(Model model, @PathVariable("hotelId") Long hotelId) {
        Hotel hotel = hotelRepository.findOne(hotelId);
        model.addAttribute("hotel", hotel);
        model.addAttribute("countryList", countryRepository.findAll());
        model.addAttribute("hotelTypeList", hotelTypeRepository.findAll());
        model.addAttribute("hotelLevelList", hotelLevelRepository.findAll());
        model.addAttribute("provinceList", provinceRepository.findByProvinceWithCountry(hotel.getHotelLocality().getLocalityDistrict().getDistrictProvince().getProvinceCountry()));
        model.addAttribute("districtList", districtRepository.findByProvinceWithProvince(hotel.getHotelLocality().getLocalityDistrict().getDistrictProvince()));
        model.addAttribute("localityList", localityRepository.findByProvinceWithDistrict(hotel.getHotelLocality().getLocalityDistrict()));
        return "hotels/hotel_edit";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    String hotelEditSave(Model model,
                        @ModelAttribute("hotel") Hotel hotel,
                        @RequestParam("uploadFileHotel") MultipartFile uploadFileHotel,
                        final RedirectAttributes redirectAttributes) {
        Resource resource = resourceLoader.getResource(Application.CLASSPATH_UPLOAD_DIR);
        try {
            String fileName;
            if (!uploadFileHotel.isEmpty()) {
                fileName = resource.getFile().getPath() + HOTEL_MASTER_UPLOAD + "/" + hotel.getHotelId() + "." + uploadFileHotel.getContentType().split("/")[1];
                try {
                    byte[] bytes = uploadFileHotel.getBytes();
                    BufferedOutputStream buffStream =
                            new BufferedOutputStream(new FileOutputStream(new File(fileName)));
                    buffStream.write(bytes);
                    buffStream.close();
                    redirectAttributes.addFlashAttribute("statusSave", " You have successfully uploaded " + fileName);
                } catch (Exception e) {
                    model.addAttribute("hotel", hotel);
                    model.addAttribute("statusSave", "You failed to upload " + fileName + ": " + e.getMessage());
                    redirectAttributes.addFlashAttribute("statusSave", "You failed to upload " + fileName + ": " + e.getMessage());
                    return "redirect:/admin/hotel/";
                }
                hotel.setHotelImageUrl(HOTEL_MASTER_UPLOAD + "/" + hotel.getHotelId() + "." + uploadFileHotel.getContentType().split("/")[1]);
                hotelRepository.save(hotel);
            } else {
                hotel.setHotelImageUrl(hotelRepository.findOne(hotel.getHotelId()).getHotelImageUrl());
                hotelRepository.save(hotel);
                model.addAttribute("statusSave", "success");
                redirectAttributes.addFlashAttribute("statusSave", "success");
                return "redirect:/admin/hotel/";
            }

        } catch (Exception ex) {
            model.addAttribute("hotel", hotel);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "hotels/hotel_edit";
        }
        return "redirect:/admin/hotel/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/status/{hotelId}", method = RequestMethod.GET)
    String hotelEnableDisable(@PathVariable("hotelId") Long hotelId) {
        Hotel h = hotelRepository.findOne(hotelId);
        h.setEnabled(h.getEnabled() == 1 ? 0 : 1);
        hotelRepository.save(h);
        return "redirect:/admin/hotel/";
    }

}
