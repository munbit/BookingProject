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
import tuk.bitong.marn.domain.Zone;
import tuk.bitong.marn.domain.ZoneRepository;

import java.util.Iterator;

/**
 * Created by muhai on 14/03/2559.
 */
@Controller
@RequestMapping(value = "admin/continent")
public class ContinentController {

    Context context = new Context();

    @Qualifier("continentRepository")
    @Autowired
    private ContinentRepository continentRepository;

    public ContinentController() {
        context.setVariable("continentList", null);
        context.setVariable("continent",new Continent());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    String  continentListPage(Model model){
        model.addAttribute("continentList",continentRepository.findAll());
        return  "continents/continent_list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.GET)
    String  continentAddPage(Model model){
        model.addAttribute("continent",new Continent());
        return  "continents/continent_add";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    String  continentAddSave(Model model,@ModelAttribute("continent") Continent continent,final RedirectAttributes redirectAttributes) {
        try {
            continentRepository.save(continent);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("continent", continent);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "continents/continent_add";
        }
        return "redirect:/admin/continent/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{continentId}" , method = RequestMethod.GET)
    String  continentEditPage(Model model,@PathVariable("continentId") Long continentId){
        model.addAttribute("continent",continentRepository.findOne(continentId));
        return  "continents/continent_edit";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit" , method = RequestMethod.POST)
    String  continentEditSave(Model model,@ModelAttribute("continent") Continent continent,final RedirectAttributes redirectAttributes) {
        try {
            continentRepository.save(continent);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("continent", continent);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "continents/continent_edit";
        }
        return "redirect:/admin/continent/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/status/{continentId}" , method = RequestMethod.GET)
    String  continentEnableDisable(@PathVariable("continentId") Long continentId){
        Continent c = continentRepository.findOne(continentId);
        c.setEnabled(c.getEnabled()==1 ? 0:1);
        continentRepository.save(c);
        return  "redirect:/admin/continent/";
    }

}
