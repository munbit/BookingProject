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
@RequestMapping(value = "admin/locality")
public class LocalityController {

    Context context = new Context();

    @Qualifier("localityRepository")
    @Autowired
    private LocalityRepository localityRepository;

    @Qualifier("districtRepository")
    @Autowired
    private DistrictRepository districtRepository;


    public LocalityController() {
        context.setVariable("districtList", null);
        context.setVariable("district",new District());
        context.setVariable("localityList", null);
        context.setVariable("locality",new Locality());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    String  localityListPage(Model model){
        model.addAttribute("localityList",localityRepository.findAll());
        return  "localitys/locality_list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.GET)
    String  localityAddPage(Model model){
        model.addAttribute("locality",new Locality());
        model.addAttribute("districtList",districtRepository.findAll());
        return  "localitys/locality_add";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    String  localityAddSave(Model model,
                            @ModelAttribute("locality") Locality locality,
                            final RedirectAttributes redirectAttributes) {
        try {
            localityRepository.save(locality);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("locality", locality);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "localitys/locality_add";
        }
        return "redirect:/admin/locality/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{localityId}" , method = RequestMethod.GET)
    String  localityEditPage(Model model,@PathVariable("localityId") Long localityId){
        model.addAttribute("locality",localityRepository.findOne(localityId));
        model.addAttribute("districtList",districtRepository.findAll());
        return  "localitys/locality_edit";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit" , method = RequestMethod.POST)
    String  localityEditSave(Model model,
                             @ModelAttribute("locality") Locality locality,
                             final RedirectAttributes redirectAttributes) {
        try {
            localityRepository.save(locality);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("locality", locality);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "localitys/locality_edit";
        }
        return "redirect:/admin/locality/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/status/{localityId}" , method = RequestMethod.GET)
    String localityEnableDisable(@PathVariable("localityId") Long localityId){
        Locality l =localityRepository.findOne(localityId);
        l.setEnabled(l.getEnabled()==1 ? 0:1);
        localityRepository.save(l);
        return  "redirect:/admin/locality/";
    }

}
