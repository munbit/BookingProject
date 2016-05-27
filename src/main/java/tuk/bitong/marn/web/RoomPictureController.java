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
@RequestMapping(value = "admin/roompicture")
public class RoomPictureController {

    Context context = new Context();


    @Qualifier("roomPictureRepository")
    @Autowired
    private RoomPictureRepository roomPictureRepository;

    @Qualifier("roomRepository")
    @Autowired
    private RoomRepository roomRepository;

    @Qualifier("pictureStoreRepository")
    @Autowired
    private PictureStoreRepository pictureStoreRepository;


    public RoomPictureController() {
        context.setVariable("roomPictureList", null);
        context.setVariable("roomPicture", new RoomPicture());
        context.setVariable("pictureStoreList", null);
        context.setVariable("pictureStore", new PictureStore());
        context.setVariable("roomList", null);
        context.setVariable("room", new Room());

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{roomId}", method = RequestMethod.GET)
    String roomPictureListPage(Model model,
                                @PathVariable("roomId") Long roomId) {
        Room vRoom = roomRepository.findOne(roomId);
        List<RoomPicture> vRoomPicture = roomPictureRepository.findByroomPictureHotelWithHotel(vRoom.getRoomHotel());
        model.addAttribute("room", vRoom);
        model.addAttribute("roomPictureList", vRoomPicture);
        model.addAttribute("pictureStoreList", pictureStoreRepository.findByPictureStoreWithHotel(vRoom.getRoomHotel()));
        return "roompictures/roompicture_list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    String roomPictureAddPage(Model model,
                               @RequestParam("roomId") Long roomId,
                               @RequestParam("pictureStoreId") Long pictureStoreId,
                               final RedirectAttributes redirectAttributes) {

        try {
            Room vRoom = roomRepository.findOne(roomId);
            PictureStore vPic = pictureStoreRepository.findOne(pictureStoreId);
            RoomPicture vRoomPic = new RoomPicture();
            vRoomPic.setRoomPictureHotel(vRoom.getRoomHotel());
            vRoomPic.setRoomPicturePictureStore(vPic);
            vRoomPic.setEnabled(1);
            roomPictureRepository.save(vRoomPic);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            Room vRoom = roomRepository.findOne(roomId);
            List<RoomPicture> vRoomPicture = roomPictureRepository.findByroomPictureHotelWithHotel(vRoom.getRoomHotel());
            model.addAttribute("room", vRoom);
            model.addAttribute("roomPictureList", vRoomPicture);
            model.addAttribute("pictureStoreList", pictureStoreRepository.findByPictureStoreWithHotel(vRoom.getRoomHotel()));
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "roompictures/roompicture_list";
        }
        return "redirect:/admin/roompicture/"+ roomId ;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/status/{roomPictureId}", method = RequestMethod.GET)
    String roomPictureEnableDisable(@PathVariable("roomPictureId") Long roomPictureId) {
        RoomPicture r = roomPictureRepository.findOne(roomPictureId);
        r.setEnabled(r.getEnabled() == 1 ? 0 : 1);
        roomPictureRepository.save(r);
        return "redirect:/admin/roompicture/";
    }

}
