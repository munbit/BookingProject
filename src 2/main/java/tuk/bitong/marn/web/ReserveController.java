package tuk.bitong.marn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;
import tuk.bitong.marn.bindings.ReserveHotelBinding;
import tuk.bitong.marn.bindings.ReserveRoomBinding;
import tuk.bitong.marn.domain.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by muhai on 12/04/2559.
 */
@Controller
@RequestMapping(value = "reserve")
public class ReserveController {

    Context context = new Context();

    @Qualifier("hotelRepository")
    @Autowired
    private HotelRepository hotelRepository;

    @Qualifier("roomRepository")
    @Autowired
    private RoomRepository roomRepository;

    @Qualifier("bookingMasterRepository")
    @Autowired
    private BookingMasterRepository bookingMasterRepository;

    @Qualifier("bookingDetailRepository")
    @Autowired
    private BookingDetailRepository bookingDetailRepository;


    @Autowired
    private MailSender mailSender;


    public ReserveController() {
        context.setVariable("hotelList", null);
        context.setVariable("hotel", new Hotel());
        context.setVariable("reserveRoomBinding", new ReserveRoomBinding());
        context.setVariable("reserveHotelBinding", new ReserveHotelBinding());

        context.setVariable("bookingMasterList", null);
        context.setVariable("bookingMaster", new BookingMaster());

        context.setVariable("bookingDetailList", null);
        context.setVariable("bookingDetail", new BookingDetail());
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/reservelist", method = RequestMethod.POST)
    String reserveList(Model model,
                       @ModelAttribute("reserveHotelBinding") ReserveHotelBinding reserveHotelBinding,
                       final RedirectAttributes redirectAttributes) {
        Double total = null;
        Hotel hotel = hotelRepository.findOne(reserveHotelBinding.getHotel().getHotelId());
        List<ReserveRoomBinding> reserveRoomList = new ArrayList<>();
        for (ReserveRoomBinding reserveHotel : reserveHotelBinding.getReserveRoomList()) {
            if (reserveHotel.getReserveRoomQuantity() > 0) {
                Room room = roomRepository.findOne(reserveHotel.getRoomId());
                ReserveRoomBinding reserveRoom = new ReserveRoomBinding(room);
                reserveRoom.setReserveRoomQuantity(reserveHotel.getReserveRoomQuantity());
                reserveRoomList.add(reserveRoom);
                if (total == null) {
                    total = reserveHotel.getReserveRoomQuantity() * room.getRoomNormalPrice();
                } else {
                    total = total + (reserveHotel.getReserveRoomQuantity() * room.getRoomNormalPrice());
                }
            }
        }

        ReserveHotelBinding reserveHotelBindingNew = new ReserveHotelBinding();
        reserveHotelBindingNew.setReserveTotalAmount(total);
        reserveHotelBindingNew.setHotel(hotel);
        reserveHotelBindingNew.setReserveRoomList(reserveRoomList);
        model.addAttribute("hotelList", hotel);
        model.addAttribute("reserveHotelBinding", reserveHotelBindingNew);
        return "reserves/reserve_main";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    String paymentCompletePage(Model model) {

        return "reserves/reserve_payment";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    String paymentSave(Model model,
                       @ModelAttribute("reserveHotelBinding") ReserveHotelBinding reserveHotelBinding,
                       final RedirectAttributes redirectAttributes) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Hotel hotel = hotelRepository.findOne(reserveHotelBinding.getHotel().getHotelId());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        BookingMaster bookingMaster = new BookingMaster();
        bookingMaster.setEnabled(1);
        bookingMaster.setBookingMasterHotel(hotel);
        if (reserveHotelBinding.getSearchEndDate().isEmpty()) {
            bookingMaster.setBookingMasterEndDate(null);
        } else {
            try {
                bookingMaster.setBookingMasterEndDate(formatter.parse(reserveHotelBinding.getSearchEndDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        bookingMaster.setBookingMasterPayMethod("Wait Pay");
        bookingMaster.setBookingMasterPayStatus("Not Pay");
        if (reserveHotelBinding.getSearchStartDate().isEmpty()) {
            bookingMaster.setBookingMasterStartDate(null);
        } else {
            try {
                bookingMaster.setBookingMasterStartDate(formatter.parse(reserveHotelBinding.getSearchStartDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        bookingMaster.setBookingMasterTotalAmount(reserveHotelBinding.getReserveTotalAmount());
        bookingMaster.setBookingMasterUser(user);
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        //Date date = new Date();
        bookingMaster.setBookingMasterDate(new Date());
        bookingMaster = bookingMasterRepository.save(bookingMaster);


        String from = "from@gmail.com";
        String to = user.getEmail();
        String subject = "Payment invoice number # " + bookingMaster.getBookingMasterId();
        String body = "Thanks you for your booking  " + user.getUserFullName() + "\n";
        body += "Payment invoice number : #" + bookingMaster.getBookingMasterId() + "\n";
        body += "Total Amount : " + bookingMaster.getBookingMasterTotalAmount() + " Bath \n";

        body += "    Room Detail \n";

        List<ReserveRoomBinding> reserveRoomList = new ArrayList<>();
        for (ReserveRoomBinding reserveHotel : reserveHotelBinding.getReserveRoomList()) {
            System.out.print("Room Id" + reserveHotel.getRoomId());
            System.out.print("Room : " + reserveHotel.getReserveRoomQuantity());
            if (reserveHotel.getReserveRoomQuantity() > 0) {
                Room room = roomRepository.findOne(reserveHotel.getRoomId());
                ReserveRoomBinding reserveRoom = new ReserveRoomBinding(room);
                reserveRoom.setReserveRoomQuantity(reserveHotel.getReserveRoomQuantity());
                reserveRoomList.add(reserveRoom);

                BookingDetail bookingDetail = new BookingDetail();
                bookingDetail.setBookingDetailBookingMaster(bookingMaster);
                bookingDetail.setEnabled(1);
                bookingDetail.setBookingdetailQuantity(reserveHotel.getReserveRoomQuantity());
                bookingDetail.setBookingDetailRoom(room);

                bookingDetail = bookingDetailRepository.save(bookingDetail);


                body += "    Room Type : " + bookingDetail.getBookingDetailRoom().getRoomRoomType().getRoomTypeNameEnglish() + " Room Quantity : " + bookingDetail.getBookingdetailQuantity() + "  \n";
            }
        }
        ReserveHotelBinding reserveHotelBindingNew = new ReserveHotelBinding();
        reserveHotelBindingNew.setReserveTotalAmount(reserveHotelBinding.getReserveTotalAmount());
        reserveHotelBindingNew.setHotel(hotel);
        reserveHotelBindingNew.setReserveRoomList(reserveRoomList);


        body += bookingMaster.getBookingMasterHotel().getHotelNameEnglish() + " \n";

        body += bookingMaster.getBookingMasterHotel().getHotelAddressEnglish()
                + " " + bookingMaster.getBookingMasterHotel().getHotelLocality().getLocalityNameEnglish()
                + " " + bookingMaster.getBookingMasterHotel().getHotelLocality().getLocalityDistrict().getDistrictNameEnglish()
                + " " + bookingMaster.getBookingMasterHotel().getHotelLocality().getLocalityDistrict().getDistrictProvince().getProvinceNameEnglish()
                + " " + bookingMaster.getBookingMasterHotel().getHotelLocality().getLocalityZipCode()
                + " \n";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);

        return "redirect:/reserve/payment/";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    String historyPage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<BookingMaster> bookingMaster = bookingMasterRepository.findByBookingMasterWithUser(user);
        model.addAttribute("bookingMasterList", bookingMaster);
        return "reserves/reserve_history";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/payconfirm/{bookingMasterId}", method = RequestMethod.GET)
    String paymentConfirmPage(Model model,
                              @PathVariable("bookingMasterId") Long bookingMasterId) {
        System.out.print(bookingMasterId);
        BookingMaster bookingMaster = bookingMasterRepository.findOne(bookingMasterId);
        model.addAttribute("bookingMaster", bookingMaster);
        return "reserves/reserve_payment_confirm";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/payconfirm", method = RequestMethod.POST)
    String paymentConfirmSave(Model model,
                              @ModelAttribute("bookingMaster") BookingMaster bookingMaster) {
        System.out.print(bookingMaster.getBookingMasterId());
        BookingMaster bookingMasterDB = bookingMasterRepository.findOne(bookingMaster.getBookingMasterId());
        bookingMasterDB.setBookingMasterPaypalTransactionNumber(bookingMaster.getBookingMasterPaypalTransactionNumber());
        bookingMasterDB.setBookingMasterPayDate(new Date());
        bookingMasterDB.setBookingMasterPayMethod("Wait Admin Confirm");
        bookingMasterDB.setBookingMasterPayStatus("Pay Confirm Complete");
        bookingMasterRepository.save(bookingMasterDB);
        return "redirect:/reserve/payment/";
    }
}
