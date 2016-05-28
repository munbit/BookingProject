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
import tuk.bitong.marn.domain.BedType;
import tuk.bitong.marn.domain.BedTypeRepository;
import tuk.bitong.marn.domain.FacilityType;
import tuk.bitong.marn.domain.FacilityTypeRepository;

/**
 * Created by muhai on 14/03/2559.
 */
@Controller
@RequestMapping(value = "admin/facilitytype")
public class FacilityTypeController {

    Context context = new Context();

    @Qualifier("facilityTypeRepository")
    @Autowired
    private FacilityTypeRepository facilityTypeRepository;

    public FacilityTypeController() {
        context.setVariable("facilityTypeList", null);
        context.setVariable("facilityType",new FacilityType());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    String  facilityTypeListPage(Model model){
        model.addAttribute("facilityTypeList",facilityTypeRepository.findAll());
        return  "facilitytypes/facilitytype_list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.GET)
    String  facilityTypeAddPage(Model model){
        model.addAttribute("facilityType",new FacilityType());
        return  "facilitytypes/facilitytype_add";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    String  facilityTypeAddSave(Model model,@ModelAttribute("facilityType") FacilityType facilityType,final RedirectAttributes redirectAttributes) {
        try {
            facilityTypeRepository.save(facilityType);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("facilityType", facilityType);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "facilitytypes/facilitytype_add";
        }
        return "redirect:/admin/facilitytype/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{facilityTypeId}" , method = RequestMethod.GET)
    String  facilityTypeEditPage(Model model,@PathVariable("facilityTypeId") Long facilityTypeId){
        model.addAttribute("facilityType",facilityTypeRepository.findOne(facilityTypeId));
        return  "facilitytypes/facilitytype_edit";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit" , method = RequestMethod.POST)
    String  facilityTypeEditSave(Model model,@ModelAttribute("facilityType") FacilityType facilityType,final RedirectAttributes redirectAttributes) {
        try {
            facilityTypeRepository.save(facilityType);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("facilityType", facilityType);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "facilitytypes/facilitytype_edit";
        }
        return "redirect:/admin/facilitytype/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/status/{facilityTypeId}" , method = RequestMethod.GET)
    String  facilityTypeEnableDisable(@PathVariable("facilityTypeId") Long facilityTypeId){
        FacilityType f = facilityTypeRepository.findOne(facilityTypeId);
        f.setEnabled(f.getEnabled()==1 ? 0:1);
        facilityTypeRepository.save(f);
        return  "redirect:/admin/facilitytype/";
    }

}
