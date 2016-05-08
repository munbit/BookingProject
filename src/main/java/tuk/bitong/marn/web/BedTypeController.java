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
import tuk.bitong.marn.domain.RoomType;
import tuk.bitong.marn.domain.RoomTypeRepository;

/**
 * Created by muhai on 14/03/2559.
 */
@Controller
@RequestMapping(value = "admin/bedtype")
public class BedTypeController {

    Context context = new Context();

    @Qualifier("bedTypeRepository")
    @Autowired
    private BedTypeRepository bedTypeRepository;

    public BedTypeController() {
        context.setVariable("bedTypeList", null);
        context.setVariable("bedType",new BedType());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    String  bedTypeListPage(Model model){
        model.addAttribute("bedTypeList",bedTypeRepository.findAll());
        return  "bedtypes/bedtype_list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.GET)
    String  bedTypeAddPage(Model model){
        model.addAttribute("bedType",new BedType());
        return  "bedtypes/bedtype_add";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    String  bedTypeAddSave(Model model,@ModelAttribute("bedType") BedType bedType,final RedirectAttributes redirectAttributes) {
        try {
            bedTypeRepository.save(bedType);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("bedType", bedType);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "bedtypes/bedtype_add";
        }
        return "redirect:/admin/bedtype/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{bedTypeId}" , method = RequestMethod.GET)
    String  bedTypeEditPage(Model model,@PathVariable("bedTypeId") Long bedTypeId){
        model.addAttribute("bedType",bedTypeRepository.findOne(bedTypeId));
        return  "bedtypes/bedtype_edit";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit" , method = RequestMethod.POST)
    String  bedTypeEditSave(Model model,@ModelAttribute("bedType") BedType bedType,final RedirectAttributes redirectAttributes) {
        try {
            bedTypeRepository.save(bedType);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("bedType", bedType);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "bedtypes/bedtype_edit";
        }
        return "redirect:/admin/bedtype/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/status/{roomTypeId}" , method = RequestMethod.GET)
    String  bedTypeEnableDisable(@PathVariable("bedTypeId") Long bedTypeId){
        BedType b = bedTypeRepository.findOne(bedTypeId);
        b.setEnabled(b.getEnabled()==1 ? 0:1);
        bedTypeRepository.save(b);
        return  "redirect:/admin/bedtype/";
    }

}
