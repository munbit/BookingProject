
package tuk.bitong.marn.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by MUHAIMAN-HENG on 2/22/2016 AD.
 */
@Controller
public class LoginController {

    @RequestMapping( "/login")
    String home(Model model) {
        return "logins/login";
    }


}

