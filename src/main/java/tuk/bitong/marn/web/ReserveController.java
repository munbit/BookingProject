package tuk.bitong.marn.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by muhai on 12/04/2559.
 */
@Controller
@RequestMapping(value = "reserve")
public class ReserveController {

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    String  reserve(Model model){
       // model.addAttribute("users","");
        return  "reserves/reserve_main";
    }
}
