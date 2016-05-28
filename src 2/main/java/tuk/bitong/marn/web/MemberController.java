package tuk.bitong.marn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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
 * Created by MUHAIMAN-HENG on 5/26/2016 AD.
 */


@Controller
@RequestMapping(value = "member")
public class MemberController {

    Context context = new Context();

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Qualifier("userRolesRepository")
    @Autowired
    private UserRolesRepository userRolesRepository;

    @Autowired
    private MailSender mailSender;

    public MemberController() {
        context.setVariable("user",new User());
    }

    @RequestMapping(value = "/signup" , method = RequestMethod.GET)
    String  memberSignupPage(Model model){
        model.addAttribute("user",new User());
        return  "members/member_signup";
    }

    @RequestMapping(value = "/signup" , method = RequestMethod.POST)
    String  memberSignupSave(Model model,
                             @ModelAttribute("user") User user,
                             final RedirectAttributes redirectAttributes){
        try{
            org.springframework.security.crypto.password.PasswordEncoder encoder
                    = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
            String passwordEncode = encoder.encode(user.getPassword());
            user.setPassword(passwordEncode);
            user.setEnabled(0);
            User uReg= userRepository.save(user);
            UserRole ur = new UserRole();
            ur.setUserId(user.getUserId());
            ur.setRole("ROLE_USER");
            userRolesRepository.save(ur);

            SimpleMailMessage message = new SimpleMailMessage();
            String from = "from@gmail.com";
            String to = user.getEmail();
            String subject = "Activate User For Nida BookingMaster";
            String body = "Welcome Nida BookingMaster " + uReg.getUserFullName() + " Please Click Activate   http://localhost:8080/member/active/" + uReg.getUserId();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);

            redirectAttributes.addFlashAttribute("saveUser", "success");
        }catch (Exception ex){
            model.addAttribute("user",user);
            model.addAttribute("saveUser","unsuccess");
            redirectAttributes.addFlashAttribute("saveUser", "unsuccess");
            return  "members/member_signup";
        }
        return  "redirect:/";
    }

    @RequestMapping(value = "/active/{userId}" , method = RequestMethod.GET)
    String  memberActive(Model model,
                         @PathVariable("userId") Long userId){
        User user = userRepository.findOne(userId);
        user.setEnabled(1);
        userRepository.save(user);
        return  "redirect:/";
    }

}
