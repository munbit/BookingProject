
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
import tuk.bitong.marn.domain.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MUHAIMAN-HENG on 03/02/2559.
 */
@Controller
@RequestMapping(value = "/")
//@SessionAttributes("hotelSearchBinding")
public class IndexController {

    Context context = new Context();

    @Qualifier("hotelRepository")
    @Autowired
    private HotelRepository hotelRepository;

    @Qualifier("roomRepository")
    @Autowired
    private RoomRepository roomRepository;


    public IndexController() {
        context.setVariable("hotelList", null);
        context.setVariable("hotel", new Hotel());
        context.setVariable("hotelSearchBinding", new HotelSearchBinding());
        context.setVariable("reserveHotelBinding", new ReserveHotelBinding());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String homeListPage(Model model,
                        @ModelAttribute("hotelSearchBinding") HotelSearchBinding hotelSearchBinding,
                        final RedirectAttributes redirectAttributes) {
        model.addAttribute("hotelList", hotelRepository.findAll());
        model.addAttribute("hotelSearchBinding", hotelSearchBinding);
        return "frontends/index";
    }

    @RequestMapping(value = "searchhotel", method = RequestMethod.POST)
    String searchListPage(Model model,
                          @ModelAttribute("hotelSearchBinding") HotelSearchBinding hotelSearchBinding,
                          final RedirectAttributes redirectAttributes) {
        model.addAttribute("hotelList", hotelRepository.findByAllName(hotelSearchBinding.getSearchHotelName()));
        model.addAttribute("hotelSearchBinding", hotelSearchBinding);
        return "frontends/index";
    }

    @RequestMapping(value = "roomdetail/{hotelId}", method = RequestMethod.GET)
    String roomDetailPage(Model model,
                          @PathVariable("hotelId") Long hotelId,
                          final RedirectAttributes redirectAttributes) {
        Hotel hotel =  hotelRepository.findOne(hotelId);
        List<ReserveRoomBinding> reserveRoomList = new ArrayList<>();
        List<Room> roomList = roomRepository.findByRoomWithHotel(hotel);
        for(Room room:roomList) {
            ReserveRoomBinding  reserveRoom = new ReserveRoomBinding(room);
            reserveRoomList.add(reserveRoom);

           // System.out.println("1 Room ID: " + reserveRoom.getRoomId());
        }
        ReserveHotelBinding reserveHotelBinding = new ReserveHotelBinding();
        reserveHotelBinding.setHotel(hotel);
        reserveHotelBinding.setReserveRoomList(reserveRoomList);
        model.addAttribute("hotelList",hotel);
        model.addAttribute("reserveHotelBinding", reserveHotelBinding);
        return "frontends/roomdetail";
    }

}
