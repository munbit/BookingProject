package tuk.bitong.marn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import tuk.bitong.marn.domain.User;
import tuk.bitong.marn.domain.UserRepository;
import tuk.bitong.marn.domain.UserRole;
import tuk.bitong.marn.domain.UserRolesRepository;
import tuk.bitong.marn.service.CustomUserDetailsService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by muhai on 14/03/2559.
 */
@Controller
@RequestMapping(value = "admin/user")
public class UserController {

    Context context = new Context();



    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;
    @Qualifier("userRolesRepository")
    @Autowired
    private UserRolesRepository userRolesRepository;


    public UserController() {
        context.setVariable("editUser",null);
        context.setVariable("saveUser",null);
        context.setVariable("resetPassword",null);
        context.setVariable("user",new User());
        context.setVariable("obj_role",new UserRole());
        Iterable<User> u=null;
        context.setVariable("users",u);
        Iterable<UserRole> ur=null;
        context.setVariable("roles",ur);
    }

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    String  userList(Model model){
        model.addAttribute("users",userRepository.findAll());
        return  "user_list";
    }

    @RequestMapping(value = "/add" , method = RequestMethod.GET)
    String  userAdd(Model model){
        model.addAttribute("user",new User());
        return  "user_add";
    }

    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    String  userAddSave(Model model,@ModelAttribute("user") User user,final RedirectAttributes redirectAttributes){
        try{
            org.springframework.security.crypto.password.PasswordEncoder encoder
				= new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
            String passwordEncode = encoder.encode(user.getPassword());
            user.setPassword(passwordEncode);
            userRepository.save(user);
            UserRole ur = new UserRole();
            ur.setRole("ROLE_MANAGER");
            ur.setUserId(user.getUserId());
            userRolesRepository.save(ur);
            UserRole ur2 = new UserRole();
            ur2.setUserId(user.getUserId());
            ur2.setRole("ROLE_USER");
            userRolesRepository.save(ur2);
            redirectAttributes.addFlashAttribute("saveUser", "success");
        }catch (Exception ex){
            model.addAttribute("user",user);
            model.addAttribute("saveUser","unsuccess");
            redirectAttributes.addFlashAttribute("saveUser", "unsuccess");
            return  "user_add";
        }
        return  "redirect:/admin/user/";
    }

    @RequestMapping(value = "/edit/{userId}" , method = RequestMethod.GET)
    String  userEditPage(Model model,@PathVariable("userId") Long userId){
        model.addAttribute("user",userRepository.findOne(userId));
        return  "user_edit";
    }

    @RequestMapping(value = "/edit" , method = RequestMethod.POST)
    String  userEditSave(Model model,@ModelAttribute("user") User user,
                         final RedirectAttributes redirectAttributes){
        try{
            User oldUser = userRepository.findOne(user.getUserId());
            oldUser.setUserFullName(user.getUserFullName());
            oldUser.setEmail(user.getEmail());
            oldUser.setEnabled(user.getEnabled());
            userRepository.save(oldUser);
            redirectAttributes.addFlashAttribute("editUser", "success");
        }catch (Exception ex){
            model.addAttribute("user",user);
            model.addAttribute("editUser","unsuccess");
            redirectAttributes.addFlashAttribute("editUser", "unsuccess");
            return  "user_edit";
        }
        return  "redirect:/admin/user/";
    }

    @RequestMapping(value = "/status/{userId}" , method = RequestMethod.GET)
    String  userEnableDisable(@PathVariable("userId") Long userId){
        User u = userRepository.findOne(userId);
        u.setEnabled(u.getEnabled()==1 ? 0:1);
        userRepository.save(u);
        return  "redirect:/admin/user/";
    }

    @RequestMapping(value = "/resetpassword/{userId}" , method = RequestMethod.GET)
    String  userResetPasswordPage(Model model,@PathVariable("userId") Long userId){
        model.addAttribute("user",userRepository.findOne(userId));
        return  "user_resetpassword";
    }

    @RequestMapping(value = "/resetpassword" , method = RequestMethod.POST)
    String  userResetPasswordSave(@ModelAttribute("user") User user,
                                  final RedirectAttributes redirectAttributes){
        org.springframework.security.crypto.password.PasswordEncoder encoder
                = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        String passwordEncode = encoder.encode(user.getPassword());
        user.setPassword(passwordEncode);
        User oldUser = userRepository.findOne(user.getUserId());
        oldUser.setPassword(passwordEncode);
        userRepository.save(oldUser);
        redirectAttributes.addFlashAttribute("resetPassword", "success");
        return  "redirect:/admin/user/";
    }

    @RequestMapping(value = "/userrole/{userId}" , method = RequestMethod.GET)
    String  userRoleList(Model model,@PathVariable("userId") Long userId){
        model.addAttribute("user",userRepository.findOne(userId));
        model.addAttribute("roles",userRolesRepository.findRoleByUserId(userId));
        model.addAttribute("obj_role",userRolesRepository.findOne((long)1));
        return  "user_role_list";
    }

    @RequestMapping(value = "/userrole/add/{userId}" , method = RequestMethod.GET)
    String  userRoleAddPage(Model model,@PathVariable("userId") Long userId){
        UserRole ur = new UserRole();
        ur.setUserId(userId);
        model.addAttribute("obj_role",ur);
        model.addAttribute("user",userRepository.findOne(userId));
        return  "user_role_add";
    }

    @RequestMapping(value = "/userrole/add" , method = RequestMethod.POST)
    String  userRoleAddSave(Model model,@ModelAttribute("obj_role") UserRole role,final RedirectAttributes redirectAttributes){
        try{
            userRolesRepository.save(role);
            redirectAttributes.addFlashAttribute("saveUser", "success");
        }catch (Exception ex){
            model.addAttribute("obj_role",role);
            model.addAttribute("user",userRepository.findOne(role.getUserId()));
            model.addAttribute("saveUser","unsuccess");
            redirectAttributes.addFlashAttribute("saveUser", "unsuccess");
            return  "user_role_add";
        }
        return  "redirect:/admin/user/userrole/" +role.getUserId() ;
    }
}
