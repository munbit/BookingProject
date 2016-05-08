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
@RequestMapping(value = "admin/district")
public class DistrictController {

    Context context = new Context();

    @Qualifier("provinceRepository")
    @Autowired
    private ProvinceRepository provinceRepository;

    @Qualifier("districtRepository")
    @Autowired
    private DistrictRepository districtRepository;


    public DistrictController() {
        context.setVariable("districtList", null);
        context.setVariable("district",new District());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    String  districtListPage(Model model){
        model.addAttribute("districtList",districtRepository.findAll());
        return  "districts/district_list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.GET)
    String  districtAddPage(Model model){
        model.addAttribute("district",new District());
        model.addAttribute("provinceList",provinceRepository.findAll());
        return  "districts/district_add";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    String  districtAddSave(Model model,@ModelAttribute("district") District district,final RedirectAttributes redirectAttributes) {
        try {
            districtRepository.save(district);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("district", district);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "districts/district_add";
        }
        return "redirect:/admin/district/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{districtId}" , method = RequestMethod.GET)
    String  districtEditPage(Model model,@PathVariable("districtId") Long districtId){
        model.addAttribute("district",districtRepository.findOne(districtId));
        model.addAttribute("provinceList",provinceRepository.findAll());
        return  "districts/district_edit";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit" , method = RequestMethod.POST)
    String  districtEditSave(Model model,@ModelAttribute("district") District district,final RedirectAttributes redirectAttributes) {
        try {
            districtRepository.save(district);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("district", district);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "districts/district_edit";
        }
        return "redirect:/admin/district/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/status/{districtId}" , method = RequestMethod.GET)
    String districtEnableDisable(@PathVariable("districtId") Long districtId){
        District d =districtRepository.findOne(districtId);
        d.setEnabled(d.getEnabled()==1 ? 0:1);
        districtRepository.save(d);
        return  "redirect:/admin/district/";
    }

}
