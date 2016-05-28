package tuk.bitong.marn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;
import tuk.bitong.marn.domain.*;

/**
 * Created by muhai on 14/03/2559.
 */
@Controller
@RequestMapping(value = "admin/room")
public class RoomController {

    Context context = new Context();

    @Qualifier("roomRepository")
    @Autowired
    private RoomRepository roomRepository;

    @Qualifier("hotelRepository")
    @Autowired
    private HotelRepository hotelRepository;

    @Qualifier("roomTypeRepository")
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Qualifier("bedTypeRepository")
    @Autowired
    private BedTypeRepository bedTypeRepository;

    public RoomController() {
        context.setVariable("bedTypeList", null);
        context.setVariable("bedType",new BedType());
        context.setVariable("roomTypeList", null);
        context.setVariable("roomType",new RoomType());
        context.setVariable("roomList", null);
        context.setVariable("room",new Room());
        context.setVariable("hotelList", null);
        context.setVariable("hotel",new Hotel());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{hotelId}" , method = RequestMethod.GET)
    String  roomListPage(Model model,
                         @PathVariable("hotelId") Long hotelId){
        Hotel h =hotelRepository.findOne(hotelId);
        model.addAttribute("hotel",h);
        model.addAttribute("roomList",roomRepository.findByRoomWithHotel(h));
        return  "rooms/room_list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add/{hotelId}" , method = RequestMethod.GET)
    String  roomAddPage(Model model,
                        @PathVariable("hotelId") Long hotelId){
        Hotel h = hotelRepository.findOne(hotelId);
        Room r = new Room();
        r.setRoomHotel(h);
        model.addAttribute("bedTypeList",bedTypeRepository.findAll());
        model.addAttribute("roomTypeList",roomTypeRepository.findAll());
        model.addAttribute("room",r);
        return  "rooms/room_add";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    String  roomAddSave(Model model,
                        @ModelAttribute("room") Room room,
                        final RedirectAttributes redirectAttributes) {
        try {
            roomRepository.save(room);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("room", room);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "rooms/room_add";
        }
        return "redirect:/admin/room/"+ room.getRoomHotel().getHotelId();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{roomId}" , method = RequestMethod.GET)
    String  roomEditPage(Model model,@PathVariable("roomId") Long roomId){
        Room r = roomRepository.findOne(roomId);
        model.addAttribute("bedTypeList",bedTypeRepository.findAll());
        model.addAttribute("roomTypeList",roomTypeRepository.findAll());
        model.addAttribute("room",r);
        return  "rooms/room_edit";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit" , method = RequestMethod.POST)
    String  roomEditSave(Model model,@ModelAttribute("room") Room room,
                         final RedirectAttributes redirectAttributes) {
        try {
            roomRepository.save(room);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("room", room);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "rooms/room_edit";
        }
        return "redirect:/admin/room/"+ room.getRoomHotel().getHotelId();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/status/{roomId}" , method = RequestMethod.GET)
    String  roomEnableDisable(@PathVariable("roomId") Long roomId){
        Room r = roomRepository.findOne(roomId);
        r.setEnabled(r.getEnabled()==1 ? 0:1);
        roomRepository.save(r);
        return  "redirect:/admin/room/" + r.getRoomHotel().getHotelId();
    }

}
