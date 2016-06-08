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
import tuk.bitong.marn.domain.BedType;
import tuk.bitong.marn.domain.BedTypeRepository;
import tuk.bitong.marn.domain.BookingMaster;
import tuk.bitong.marn.domain.BookingMasterRepository;

/**
 * Created by muhai on 14/03/2559.
 */
@Controller
@RequestMapping(value = "admin/booking")
public class BookingController {

    Context context = new Context();

    @Qualifier("bookingMasterRepository")
    @Autowired
    private BookingMasterRepository bookingMasterRepository;

    public BookingController() {
        context.setVariable("bookingMasterList", null);
        context.setVariable("bookingMaster",new BookingMaster());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    String  bookingListPage(Model model){
        model.addAttribute("bookingMasterList",bookingMasterRepository.findAll());
        return  "bookings/booking_list";
    }

    //Accept Payment complete

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{bookingMasterId}" , method = RequestMethod.GET)
    String  bookingConfirmPay(@PathVariable("bookingMasterId") Long bookingMasterId){
        BookingMaster b = bookingMasterRepository.findOne(bookingMasterId);
        b.setBookingMasterPayMethod("Admin Approve");
        b.setBookingMasterPayStatus("Accept Payment complete");
        bookingMasterRepository.save(b);
        return  "redirect:/admin/booking/";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/status/{bookingMasterId}" , method = RequestMethod.GET)
    String  bookingEnableDisable(@PathVariable("bookingMasterId") Long bookingMasterId){
        BookingMaster b = bookingMasterRepository.findOne(bookingMasterId);
        b.setEnabled(b.getEnabled()==1 ? 0:1);
        bookingMasterRepository.save(b);
        return  "redirect:/admin/booking/";
    }

}
