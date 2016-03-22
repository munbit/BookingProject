package tuk.bitong.marn.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by muhai on 14/03/2559.
 */
@Controller
public class AdminController {

    @RequestMapping("/admin")
    String home(){
        return "admin";
    }
}
