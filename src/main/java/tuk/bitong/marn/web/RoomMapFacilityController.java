package tuk.bitong.marn.web;

import org.codehaus.groovy.runtime.dgmimpl.arrays.LongArrayGetAtMetaMethod;
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
@RequestMapping(value = "admin/roommapfacility")
public class RoomMapFacilityController {

    Context context = new Context();

    @Qualifier("roomRepository")
    @Autowired
    private RoomRepository roomRepository;

    @Qualifier("facilityTypeRepository")
    @Autowired
    private FacilityTypeRepository facilityTypeRepository;

    @Qualifier("roomMapFacilityRepository")
    @Autowired
    private RoomMapFacilityRepository roomMapFacilityRepository;


    public RoomMapFacilityController() {
        context.setVariable("roomList", null);
        context.setVariable("room", new Room());
        context.setVariable("facilityTypeList", null);
        context.setVariable("facilityType", new FacilityType());
        context.setVariable("roomMapFacilityList", null);
        context.setVariable("roomMapFacility", new RoomMapFacility());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{roomId}", method = RequestMethod.GET)
    String roomMapFacilityListPage(Model model,
                                   @PathVariable("roomId") Long roomId) {
        Room r = roomRepository.findOne(roomId);
        model.addAttribute("room", r);
        model.addAttribute("roomMapFacilityList", roomMapFacilityRepository.findByRoomMapFacilityWithRoom(r));
        return "roommapfacilitys/roommapfacility_list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add/{roomId}", method = RequestMethod.GET)
    String roomMapFacilityAddPage(Model model,
                                  @PathVariable("roomId") Long roomId) {
        Room r = roomRepository.findOne(roomId);
        RoomMapFacility rpf = new RoomMapFacility();
        rpf.setMapRoom(r);
        model.addAttribute("roomMapFacility", rpf);
        model.addAttribute("facilityTypeList", facilityTypeRepository.findAll());
        return "roommapfacilitys/roommapfacility_add";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    String roomMapFacilityAddSave(Model model,
                                  @ModelAttribute("roomMapFacility") RoomMapFacility roomMapFacility,
                                  final RedirectAttributes redirectAttributes) {
        try {
            roomMapFacilityRepository.save(roomMapFacility);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("roomMapFacility", roomMapFacility);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "roommapfacilitys/roommapfacility_add";
        }
        return "redirect:/admin/roommapfacility/" + roomMapFacility.getMapRoom().getRoomId();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{roomMapFacilityRepositoryId}", method = RequestMethod.GET)
    String roomMapFacilityEditPage(Model model,
                                   @PathVariable("roomMapFacilityRepositoryId") Long roomMapFacilityRepositoryId) {
        model.addAttribute("roomMapFacility", roomMapFacilityRepository.findOne(roomMapFacilityRepositoryId));
        model.addAttribute("facilityTypeList", facilityTypeRepository.findAll());
        return "roommapfacilitys/roommapfacility_edit";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    String roomMapFacilityEditSave(Model model,
                                   @ModelAttribute("roomMapFacility") RoomMapFacility roomMapFacility,
                                   final RedirectAttributes redirectAttributes) {
        try {
            roomMapFacilityRepository.save(roomMapFacility);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("roomMapFacility", roomMapFacility);
            model.addAttribute("facilityTypeList", facilityTypeRepository.findAll());
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "roommapfacilitys/roommapfacility_edit";
        }
        return "redirect:/admin/roommapfacility/" + roomMapFacility.getMapRoom().getRoomId();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/status/{roomMapFacilityRepositoryId}", method = RequestMethod.GET)
    String roomMapFacilityEnableDisable(@PathVariable("roomMapFacilityRepositoryId") Long roomMapFacilityRepositoryId) {
        RoomMapFacility r = roomMapFacilityRepository.findOne(roomMapFacilityRepositoryId);
        r.setEnabled(r.getEnabled() == 1 ? 0 : 1);
        roomMapFacilityRepository.save(r);
        return "redirect:/admin/roommapfacility/" + r.getMapRoom().getRoomId();
    }

}
