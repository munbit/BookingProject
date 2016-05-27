package tuk.bitong.marn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;
import tuk.bitong.marn.Application;
import tuk.bitong.marn.domain.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by muhai on 14/03/2559.
 */
@Controller
@RequestMapping(value = "admin/hotelpicture")
public class HotelPictureController {

    Context context = new Context();

    @Qualifier("hotelPictureRepository")
    @Autowired
    private HotelPictureRepository hotelPictureRepository;

    @Qualifier("hotelRepository")
    @Autowired
    private HotelRepository hotelRepository;

    @Qualifier("pictureStoreRepository")
    @Autowired
    private PictureStoreRepository pictureStoreRepository;


    public HotelPictureController() {
        context.setVariable("hotelPictureList", null);
        context.setVariable("hotelPicture", new HotelPicture());
        context.setVariable("pictureStoreList", null);
        context.setVariable("pictureStore", new PictureStore());
        context.setVariable("hotelList", null);
        context.setVariable("hotel", new Hotel());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{hotelId}", method = RequestMethod.GET)
    String hotelPictureListPage(Model model,
                                @PathVariable("hotelId") Long hotelId) {
        Hotel vHotel = hotelRepository.findOne(hotelId);
        List<HotelPicture> vHotelPicture = hotelPictureRepository.findByhotelPictureHotelWithHotel(vHotel);
        model.addAttribute("hotel", vHotel);
        model.addAttribute("hotelPictureList", vHotelPicture);
        model.addAttribute("pictureStoreList", pictureStoreRepository.findByPictureStoreWithHotel(vHotel));
        return "hotelpictures/hotelpicture_list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    String hotelPictureAddPage(Model model,
                               @RequestParam("hotelId") Long hotelId,
                               @RequestParam("pictureStoreId") Long pictureStoreId,
                               final RedirectAttributes redirectAttributes) {

        try {
            Hotel vHotel = hotelRepository.findOne(hotelId);
            PictureStore vPic = pictureStoreRepository.findOne(pictureStoreId);
            HotelPicture vHotelPic = new HotelPicture();
            vHotelPic.setHotelPictureHotel(vHotel);
            vHotelPic.setHotelPicturePictureStore(vPic);
            vHotelPic.setEnabled(1);
            hotelPictureRepository.save(vHotelPic);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            Hotel vHotel = hotelRepository.findOne(hotelId);
            List<HotelPicture> vHotelPicture = hotelPictureRepository.findByhotelPictureHotelWithHotel(vHotel);
            model.addAttribute("hotel", vHotel);
            model.addAttribute("hotelPictureList", vHotelPicture);
            model.addAttribute("pictureStoreList", pictureStoreRepository.findByPictureStoreWithHotel(vHotel));
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "hotelpictures/hotelpicture_list";
        }
        return "redirect:/admin/hotelpicture/"+ hotelId ;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/status/{hotelPictureId}", method = RequestMethod.GET)
    String hotelPictureEnableDisable(@PathVariable("hotelPictureId") Long hotelPictureId) {
        HotelPicture h = hotelPictureRepository.findOne(hotelPictureId);
        h.setEnabled(h.getEnabled() == 1 ? 0 : 1);
        hotelPictureRepository.save(h);
        return "redirect:/admin/hotelpicture/" + h.getHotelPictureHotel().getHotelId();
    }

}
