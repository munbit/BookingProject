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

import java.util.Iterator;

/**
 * Created by muhai on 14/03/2559.
 */
@Controller
@RequestMapping(value = "admin/zone")
public class ZoneController {

    Context context = new Context();

    @Qualifier("zoneRepository")
    @Autowired
    private ZoneRepository zoneRepository;

    public ZoneController() {
        context.setVariable("statusSave",null);
        Iterable<Zone> z= new Iterable<Zone>() {
            @Override
            public Iterator<Zone> iterator() {
                return null;
            }
        };
        context.setVariable("zoneList",z);
        context.setVariable("zone",new Zone());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    String  zoneList(Model model){
        model.addAttribute("zoneList",zoneRepository.findAll());
        return  "zones/zone_list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.GET)
    String  zoneAddPage(Model model){
        model.addAttribute("zone",new Zone());
        return  "zones/zone_add";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    String  zoneAddSave(Model model,@ModelAttribute("zone") Zone zone,final RedirectAttributes redirectAttributes) {
        try {
            zoneRepository.save(zone);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("zone", zone);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "zones/zone_add";
        }
        return "redirect:/admin/zone/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{zoneId}" , method = RequestMethod.GET)
    String  zoneEditPage(Model model,@PathVariable("zoneId") Long zoneId){
        model.addAttribute("zone",zoneRepository.findOne(zoneId));
        return  "zones/zone_edit";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit" , method = RequestMethod.POST)
    String  zoneEditSave(Model model,@ModelAttribute("zone") Zone zone,final RedirectAttributes redirectAttributes) {
        try {
            zoneRepository.save(zone);
            redirectAttributes.addFlashAttribute("statusSave", "success");
        } catch (Exception ex) {
            model.addAttribute("zone", zone);
            model.addAttribute("statusSave", "unsuccess");
            redirectAttributes.addFlashAttribute("statusSave", "unsuccess");
            return "zones/zone_edit";
        }
        return "redirect:/admin/zone/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/status/{zoneId}" , method = RequestMethod.GET)
    String  zoneEnableDisable(@PathVariable("zoneId") Long zoneId){
        Zone z = zoneRepository.findOne(zoneId);
        z.setEnabled(z.getEnabled()==1 ? 0:1);
        zoneRepository.save(z);
        return  "redirect:/admin/zone/";
    }

}
