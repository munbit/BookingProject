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
import tuk.bitong.marn.domain.Country;
import tuk.bitong.marn.domain.CountryRepository;

/**
 * Created by muhai on 14/03/2559.
 */
@Controller
@RequestMapping(value = "admin/country")
public class CountryController {

    Context context = new Context();

    @Qualifier("countryRepository")
    @Autowired
    private CountryRepository countryRepository;

    @Qualifier("continentRepository")
    @Autowired
    private ContinentRepository continentRepository;

    public CountryController() {
        context.setVariable("countryList", null);
        context.setVariable("country",new Country());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    String  countryListPage(Model model){
        model.addAttribute("countryList",countryRepository.findAll());
        return  "countrys/country_list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.GET)
    String  countryAddPage(Model model){
        model.addAttribute("country",new Country());
        model.addAttribute("continentList",continentRepository.findAll());
        return  "countrys/country_add";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    String  countryAddSave(Model model,@ModelAttribute("country") Country country,final RedirectAttributes redirectAttributes) {
        try {
            countryRepository.save(country);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("country", country);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "countrys/country_add";
        }
        return "redirect:/admin/country/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{countryId}" , method = RequestMethod.GET)
    String  countryEditPage(Model model,@PathVariable("countryId") Long countryId){
        model.addAttribute("country",countryRepository.findOne(countryId));
        model.addAttribute("continentList",continentRepository.findAll());
        return  "countrys/country_edit";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit" , method = RequestMethod.POST)
    String  countryEditSave(Model model,@ModelAttribute("country") Country country,final RedirectAttributes redirectAttributes) {
        try {
            countryRepository.save(country);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("country", country);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "countrys/country_edit";
        }
        return "redirect:/admin/country/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/status/{countryId}" , method = RequestMethod.GET)
    String  countryEnableDisable(@PathVariable("countryId") Long countryId){
        Country c = countryRepository.findOne(countryId);
        c.setEnabled(c.getEnabled()==1 ? 0:1);
        countryRepository.save(c);
        return  "redirect:/admin/country/";
    }

}
