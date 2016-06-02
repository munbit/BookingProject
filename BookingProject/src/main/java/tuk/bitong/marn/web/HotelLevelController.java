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
import tuk.bitong.marn.domain.HotelLevel;
import tuk.bitong.marn.domain.HotelLevelRepository;
import tuk.bitong.marn.domain.HotelType;
import tuk.bitong.marn.domain.HotelTypeRepository;

/**
 * Created by muhai on 14/03/2559.
 */
@Controller
@RequestMapping(value = "admin/hotellevel")
public class HotelLevelController {

    Context context = new Context();

    @Qualifier("hotelLevelRepository")
    @Autowired
    private HotelLevelRepository hotelLevelRepository;

    public HotelLevelController() {
        context.setVariable("hotelLevelList", null);
        context.setVariable("hotelLevel",new HotelLevel());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    String  hotelLevelListPage(Model model){
        model.addAttribute("hotelLevelList",hotelLevelRepository.findAll());
        return  "hotellevels/hotellevel_list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.GET)
    String  hotelLevelIdAddPage(Model model){
        model.addAttribute("hotelLevel",new HotelLevel());
        return  "hotellevels/hotellevel_add";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    String  hotelLevelAddSave(Model model,@ModelAttribute("hotelLevel")  HotelLevel  hotelLevel,final RedirectAttributes redirectAttributes) {
        try {
            hotelLevelRepository.save(hotelLevel);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("hotelLevel", hotelLevel);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "hotellevels/hotellevel_add";
        }
        return "redirect:/admin/hotellevel/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{hotelLevelId}" , method = RequestMethod.GET)
    String   hotelLevelEditPage(Model model,@PathVariable("hotelLevelId") Long  hotelLevelId){
        model.addAttribute("hotelLevel", hotelLevelRepository.findOne(hotelLevelId));
        return  "hotellevels/hotellevel_edit";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit" , method = RequestMethod.POST)
    String   hotelLevelEditSave(Model model,@ModelAttribute("hotelLevel")  HotelLevel  hotelLevel,final RedirectAttributes redirectAttributes) {
        try {
            hotelLevelRepository.save(hotelLevel);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("hotelLevel", hotelLevel);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "hotellevels/hotellevel_edit";
        }
        return "redirect:/admin/hotellevel/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/status/{hotelLevelId}" , method = RequestMethod.GET)
    String  continentEnableDisable(@PathVariable("hotelLevelId") Long  hotelLevelId){
        HotelLevel t =  hotelLevelRepository.findOne(hotelLevelId);
        t.setEnabled(t.getEnabled()==1 ? 0:1);
        hotelLevelRepository.save(t);
        return  "redirect:/admin/hotellevel/";
    }

}
