
package tuk.bitong.marn.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by MUHAIMAN-HENG on 03/02/2559.
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    String home() {
        return "index";
    }

}
