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
import tuk.bitong.marn.domain.HotelType;
import tuk.bitong.marn.domain.HotelTypeRepository;
import tuk.bitong.marn.domain.RoomType;
import tuk.bitong.marn.domain.RoomTypeRepository;

/**
 * Created by muhai on 14/03/2559.
 */
@Controller
@RequestMapping(value = "admin/roomtype")
public class RoomTypeController {

    Context context = new Context();

    @Qualifier("roomTypeRepository")
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    public RoomTypeController() {
        context.setVariable("roomTypeList", null);
        context.setVariable("roomType",new RoomType());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    String  roomTypeListPage(Model model){
        model.addAttribute("roomTypeList",roomTypeRepository.findAll());
        return  "roomtypes/roomtype_list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.GET)
    String  roomTypeAddPage(Model model){
        model.addAttribute("roomType",new RoomType());
        return  "roomtypes/roomtype_add";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    String  roomTypeAddSave(Model model,@ModelAttribute("roomType") RoomType roomType,final RedirectAttributes redirectAttributes) {
        try {
            roomTypeRepository.save(roomType);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("roomType", roomType);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "roomtypes/roomtype_add";
        }
        return "redirect:/admin/roomtype/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{roomTypeId}" , method = RequestMethod.GET)
    String  roomTypeEditPage(Model model,@PathVariable("roomTypeId") Long roomTypeId){
        model.addAttribute("roomType",roomTypeRepository.findOne(roomTypeId));
        return  "roomtypes/roomtype_edit";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit" , method = RequestMethod.POST)
    String  roomTypeEditSave(Model model,@ModelAttribute("roomType") RoomType roomType,final RedirectAttributes redirectAttributes) {
        try {
            roomTypeRepository.save(roomType);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("roomType", roomType);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "roomtypes/roomtype_edit";
        }
        return "redirect:/admin/roomtype/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/status/{roomTypeId}" , method = RequestMethod.GET)
    String  roomTypeEnableDisable(@PathVariable("roomTypeId") Long roomTypeId){
        RoomType r = roomTypeRepository.findOne(roomTypeId);
        r.setEnabled(r.getEnabled()==1 ? 0:1);
        roomTypeRepository.save(r);
        return  "redirect:/admin/roomtype/";
    }

}
