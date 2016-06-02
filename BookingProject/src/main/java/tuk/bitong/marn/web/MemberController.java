package tuk.bitong.marn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;
import tuk.bitong.marn.bindings.HotelSearchBinding;
import tuk.bitong.marn.domain.*;

import java.util.List;

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
        model.addAttribute("hotelSearchBinding", new HotelSearchBinding());
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
            model.addAttribute("hotelSearchBinding", new HotelSearchBinding());
            redirectAttributes.addFlashAttribute("saveUser", "success");
        }catch (Exception ex){
            model.addAttribute("user",user);
            model.addAttribute("saveUser","unsuccess");
            redirectAttributes.addFlashAttribute("saveUser", "unsuccess");
            model.addAttribute("hotelSearchBinding", new HotelSearchBinding());
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
        model.addAttribute("hotelSearchBinding", new HotelSearchBinding());
        return  "redirect:/";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    String profilePage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("hotelSearchBinding", new HotelSearchBinding());
        return "members/member_profile";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    String profileEditPage(Model model,@ModelAttribute("user") User user,
                           final RedirectAttributes redirectAttributes){
        try {
            org.springframework.security.crypto.password.PasswordEncoder encoder
                    = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
            String passwordEncode = encoder.encode(user.getPassword());
            user.setPassword(passwordEncode);
            User oldUser = userRepository.findOne(user.getUserId());
            oldUser.setPassword(passwordEncode);
            userRepository.save(oldUser);
            redirectAttributes.addFlashAttribute("resetPassword", "success");

            model.addAttribute("hotelSearchBinding", new HotelSearchBinding());
        } catch (Exception ex){

            model.addAttribute("hotelSearchBinding", new HotelSearchBinding());
            redirectAttributes.addFlashAttribute("resetPassword", "Fail");
        }
        return "members/member_profile";
    }


}
