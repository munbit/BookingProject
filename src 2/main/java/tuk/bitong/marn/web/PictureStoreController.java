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

/**
 * Created by muhai on 14/03/2559.
 */
@Controller
@RequestMapping(value = "admin/picturestore")
public class PictureStoreController {

    Context context = new Context();

    @Qualifier("pictureStoreRepository")
    @Autowired
    private PictureStoreRepository pictureStoreRepository;

    @Qualifier("hotelRepository")
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    public static String PICTURE_STORE_UPLOAD = "/picture_store";
    //public static String CLASSPATH_UPLOAD_DIR ="classpath:static/upload-dir";

    public PictureStoreController() {
        context.setVariable("pictureStoreList", null);
        context.setVariable("pictureStore",new PictureStore());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{hotelId}" , method = RequestMethod.GET)
    String  pictureStoreListPage(Model model,
                                 @PathVariable("hotelId") Long hotelId){
        Hotel hotel = hotelRepository.findOne(hotelId);
        model.addAttribute("hotel", hotel);
        model.addAttribute("pictureStoreList",pictureStoreRepository.findByPictureStoreWithHotel(hotel));
        Resource resource = resourceLoader.getResource(Application.CLASSPATH_UPLOAD_DIR);

        File file = null;

        try {
            //System.out.println(resource.getFile().getPath() + HOTEL_MASTER_UPLOAD);
            file = new File(resource.getFile().getPath() + PICTURE_STORE_UPLOAD);
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
        return  "picturestores/picturestore_list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/addmulti/{hotelId}" , method = RequestMethod.GET)
    String  pictureStoreAddPage(Model model,
                                @PathVariable("hotelId") Long hotelId){
        model.addAttribute("pictureStore",new PictureStore());
        model.addAttribute("hotel", hotelRepository.findOne(hotelId));
        return  "picturestores/picturestore_addmulti";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    String pictureStoreAddSave(Model model,
                        @RequestParam("hotelId") Long hotelId,
                        @RequestParam("uploadPictureStore") MultipartFile uploadPictureStore,
                        final RedirectAttributes redirectAttributes) {
        Resource resource = resourceLoader.getResource(Application.CLASSPATH_UPLOAD_DIR);
        Hotel h = hotelRepository.findOne(hotelId);
        try {
            PictureStore newPic  = new PictureStore();
            newPic.setEnabled(1);
            newPic.setPictureStoreHotel(h);
            newPic.setPictureStorePath("");
            PictureStore p = pictureStoreRepository.save(newPic);
            String fileName ="";
            if (!uploadPictureStore.isEmpty()) {
                try {
                    fileName = resource.getFile().getPath() + PICTURE_STORE_UPLOAD + "/" + p.getPictureStoreId() + "." + uploadPictureStore.getContentType().split("/")[1];
                    byte[] bytes = uploadPictureStore.getBytes();
                    BufferedOutputStream buffStream =
                            new BufferedOutputStream(new FileOutputStream(new File(fileName)));
                    buffStream.write(bytes);
                    buffStream.close();
                    p.setPictureStorePath(PICTURE_STORE_UPLOAD + "/" + p.getPictureStoreId() + "." + uploadPictureStore.getContentType().split("/")[1]);
                    pictureStoreRepository.save(p);
                    redirectAttributes.addFlashAttribute("statusSave", " You have successfully uploaded " + fileName);
                } catch (Exception e) {
                    model.addAttribute("hotel", h);
                    model.addAttribute("statusSave", "You failed to upload " + fileName + ": " + e.getMessage());
                    redirectAttributes.addFlashAttribute("statusSave", "You failed to upload " + fileName + ": " + e.getMessage());
                    return "redirect:/admin/picturestore/" + hotelId;
                }
            } else {
                model.addAttribute("hotel", h);
                model.addAttribute("statusSave", "success");
                redirectAttributes.addFlashAttribute("statusSave", "success");
                return "redirect:/admin/picturestore/" + hotelId;
            }

        } catch (Exception ex) {
            model.addAttribute("hotel", h);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "picturestores/picturestore_add";
        }
        return "redirect:/admin/picturestore/" + h.getHotelId();
    }




    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/status/{pictureStoreId}" , method = RequestMethod.GET)
    String  pictureStoreEnableDisable(@PathVariable("pictureStoreId") Long pictureStoreId){
        PictureStore p = pictureStoreRepository.findOne(pictureStoreId);
        p.setEnabled(p.getEnabled()==1 ? 0:1);
        pictureStoreRepository.save(p);
        return  "redirect:/admin/picturestore/" + p.getPictureStoreHotel().getHotelId();
    }

}
