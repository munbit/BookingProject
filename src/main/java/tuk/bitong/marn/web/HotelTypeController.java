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
import tuk.bitong.marn.domain.Continent;
import tuk.bitong.marn.domain.ContinentRepository;
import tuk.bitong.marn.domain.HotelType;
import tuk.bitong.marn.domain.HotelTypeRepository;

/**
 * Created by muhai on 14/03/2559.
 */
@Controller
@RequestMapping(value = "admin/hoteltype")
public class HotelTypeController {

    Context context = new Context();

    @Qualifier("hotelTypeRepository")
    @Autowired
    private HotelTypeRepository hotelTypeRepository;

    public HotelTypeController() {
        context.setVariable("hotelTypeList", null);
        context.setVariable("hotelType",new HotelType());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    String  hotelTypeListPage(Model model){
        model.addAttribute("hotelTypeList",hotelTypeRepository.findAll());
        return  "hoteltypes/hoteltype_list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.GET)
    String  hotelTypeAddPage(Model model){
        model.addAttribute("hotelType",new HotelType());
        return  "hoteltypes/hoteltype_add";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    String  hotelTypeAddSave(Model model,@ModelAttribute("hotelType") HotelType hotelType,final RedirectAttributes redirectAttributes) {
        try {
            hotelTypeRepository.save(hotelType);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("hotelType", hotelType);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "hoteltypes/hoteltype_add";
        }
        return "redirect:/admin/hoteltype/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{hotelTypeId}" , method = RequestMethod.GET)
    String  hotelTypeEditPage(Model model,@PathVariable("hotelTypeId") Long hotelTypeId){
        model.addAttribute("hotelType",hotelTypeRepository.findOne(hotelTypeId));
        return  "hoteltypes/hoteltype_edit";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit" , method = RequestMethod.POST)
    String  hotelTypeEditSave(Model model,@ModelAttribute("hotelType") HotelType hotelType,final RedirectAttributes redirectAttributes) {
        try {
            hotelTypeRepository.save(hotelType);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("hotelType", hotelType);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "hoteltypes/hoteltype_edit";
        }
        return "redirect:/admin/hoteltype/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/status/{hotelTypeId}" , method = RequestMethod.GET)
    String  hotelTypeEnableDisable(@PathVariable("hotelTypeId") Long hotelTypeId){
        HotelType t = hotelTypeRepository.findOne(hotelTypeId);
        t.setEnabled(t.getEnabled()==1 ? 0:1);
        hotelTypeRepository.save(t);
        return  "redirect:/admin/hoteltype/";
    }

}
