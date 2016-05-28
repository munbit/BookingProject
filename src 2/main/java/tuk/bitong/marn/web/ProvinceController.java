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
@RequestMapping(value = "admin/province")
public class ProvinceController {

    Context context = new Context();

    @Qualifier("provinceRepository")
    @Autowired
    private ProvinceRepository provinceRepository;

    @Qualifier("countryRepository")
    @Autowired
    private CountryRepository countryRepository;

    @Qualifier("zoneRepository")
    @Autowired
    private ZoneRepository zoneRepository;

    @Qualifier("continentRepository")
    @Autowired
    private ContinentRepository continentRepository;

    public ProvinceController() {
        context.setVariable("provinceList", null);
        context.setVariable("province",new Province());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    String  provinceListPage(Model model){
        model.addAttribute("provinceList",provinceRepository.findAll());
        return  "provinces/province_list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.GET)
    String  provinceAddPage(Model model){
        model.addAttribute("province",new Province());
        model.addAttribute("zoneList",zoneRepository.findAll());
        model.addAttribute("countryList",countryRepository.findAll());
        return  "provinces/province_add";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    String  provinceAddSave(Model model,@ModelAttribute("province") Province province,final RedirectAttributes redirectAttributes) {
        try {
            provinceRepository.save(province);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("province", province);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "provinces/province_add";
        }
        return "redirect:/admin/province/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{provinceId}" , method = RequestMethod.GET)
    String  provinceEditPage(Model model,@PathVariable("provinceId") Long provinceId){
        model.addAttribute("province",provinceRepository.findOne(provinceId));
        model.addAttribute("zoneList",zoneRepository.findAll());
        model.addAttribute("countryList",countryRepository.findAll());
        return  "provinces/province_edit";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit" , method = RequestMethod.POST)
    String  provinceEditSave(Model model,@ModelAttribute("province") Province province,final RedirectAttributes redirectAttributes) {
        try {
            provinceRepository.save(province);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("province", province);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "provinces/province_edit";
        }
        return "redirect:/admin/province/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/status/{provinceId}" , method = RequestMethod.GET)
    String  provinceEnableDisable(@PathVariable("provinceId") Long provinceId){
        Province p =provinceRepository.findOne(provinceId);
        p.setEnabled(p.getEnabled()==1 ? 0:1);
        provinceRepository.save(p);
        return  "redirect:/admin/province/";
    }

}
