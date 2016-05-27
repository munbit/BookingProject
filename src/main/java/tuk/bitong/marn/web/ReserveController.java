package tuk.bitong.marn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;
import tuk.bitong.marn.bindings.HotelSearchBinding;
import tuk.bitong.marn.bindings.ReserveHotelBinding;
import tuk.bitong.marn.bindings.ReserveRoomBinding;
import tuk.bitong.marn.domain.Hotel;
import tuk.bitong.marn.domain.HotelRepository;
import tuk.bitong.marn.domain.Room;
import tuk.bitong.marn.domain.RoomRepository;

import java.util.ArrayList;
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


    public ReserveController() {
        context.setVariable("hotelList", null);
        context.setVariable("hotel", new Hotel());
        context.setVariable("reserveRoomBinding", new ReserveRoomBinding());
        context.setVariable("reserveHotelBinding", new ReserveHotelBinding());
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
            if (reserveHotel.getReserveRoomQuantity() > 0 ) {
                Room room = roomRepository.findOne(reserveHotel.getRoomId());
                ReserveRoomBinding reserveRoom = new ReserveRoomBinding(room);
                reserveRoom.setReserveRoomQuantity(reserveHotel.getReserveRoomQuantity());
                reserveRoomList.add(reserveRoom);
                if (total == null) {
                    total = reserveHotel.getReserveRoomQuantity() * room.getRoomNormalPrice();
                }else{
                    total = total+ (reserveHotel.getReserveRoomQuantity() * room.getRoomNormalPrice());
                }
                System.out.print(total);
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
}
