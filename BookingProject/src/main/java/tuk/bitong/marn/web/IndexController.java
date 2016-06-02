
package tuk.bitong.marn.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;
import tuk.bitong.marn.bindings.*;
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


    @Qualifier("hotelPictureRepository")
    @Autowired
    private HotelPictureRepository hotelPictureRepository;

    @Qualifier("roomPictureRepository")
    @Autowired
    private RoomPictureRepository roomPictureRepository;


    public IndexController() {
        context.setVariable("hotelList", null);
        context.setVariable("hotel", new Hotel());


        context.setVariable("hotelImageList", null);
        context.setVariable("hotelImage", new HotelImageBinding());

        context.setVariable("hotelBannerList", null);
        context.setVariable("hotelBanner", new Hotel());

        context.setVariable("hotelSearchBinding", new HotelSearchBinding());

        context.setVariable("hotelExtraBindingList", null);
        context.setVariable("hotelExtraBinding", new HotelExtraBinding());

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String homeListPage(Model model,
                        @ModelAttribute("hotelSearchBinding") HotelSearchBinding hotelSearchBinding,
                        final RedirectAttributes redirectAttributes) {
        List<Hotel> hotelList = (List<Hotel>) hotelRepository.findAll();
        List<HotelExtraBinding> hotelExtraBindingList = getHotelExtraBindingList(hotelList);


        model.addAttribute("hotelBannerList", hotelRepository.findAll());
        model.addAttribute("hotelExtraBindingList", hotelExtraBindingList);
        model.addAttribute("hotelList", hotelList);
        model.addAttribute("hotelSearchBinding", hotelSearchBinding);
        return "frontends/index";
    }

     List<HotelExtraBinding> getHotelExtraBindingList(List<Hotel> hotelList){
        List<HotelExtraBinding> hotelExtraBindingList = new ArrayList<>();
        for (Hotel hotel: hotelList) {
            List<HotelPicture> HotelPicture = hotelPictureRepository.findByhotelPictureHotelWithHotel(hotel);
            HotelExtraBinding hotelExtraBinding = new HotelExtraBinding(hotel);
            hotelExtraBinding.setHotelHotelPictureList(HotelPicture);
            hotelExtraBindingList.add(hotelExtraBinding);
        }
        return  hotelExtraBindingList;
    }


    @RequestMapping(value = "searchhotel", method = RequestMethod.POST)
    String searchListPage(Model model,
                          @ModelAttribute("hotelSearchBinding") HotelSearchBinding hotelSearchBinding,
                          final RedirectAttributes redirectAttributes) {

        List<Hotel> hotelList = hotelRepository.findByAllName(hotelSearchBinding.getSearchHotelName());
        List<HotelExtraBinding> hotelExtraBindingList = getHotelExtraBindingList(hotelList);

        model.addAttribute("hotelBannerList", hotelRepository.findAll());
        model.addAttribute("hotelExtraBindingList", hotelExtraBindingList);
        model.addAttribute("hotelList", hotelList);
        model.addAttribute("hotelSearchBinding", hotelSearchBinding);


        return "frontends/index";
    }

    @RequestMapping(value = "roomdetail", method = RequestMethod.GET)
    String roomDetailPage(Model model,
                          @RequestParam("hotelId") Long hotelId,
                          @RequestParam("startDate") String startDate,
                          @RequestParam("endDate") String endDate,
                          @RequestParam("searchText") String searchText,
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
        reserveHotelBinding.setSearchEndDate(endDate);
        reserveHotelBinding.setSearchStartDate(startDate);

        List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(hotel);
        List<HotelExtraBinding> hotelExtraBindingList = getHotelExtraBindingList(hotelList);
        model.addAttribute("hotelExtraBindingList", hotelExtraBindingList);

        HotelSearchBinding hotelSearchBinding = new HotelSearchBinding();
        hotelSearchBinding.setSearchHotelName(searchText);
        hotelSearchBinding.setSearchStartDate(startDate);
        hotelSearchBinding.setSearchEndDate(endDate);
        model.addAttribute("hotelList",hotel);
        model.addAttribute("reserveHotelBinding", reserveHotelBinding);
        model.addAttribute("hotelSearchBinding", hotelSearchBinding);
        return "frontends/roomdetail";
    }

    @RequestMapping(value = "hotelimage", method = RequestMethod.GET)
    String HotelImagePage(Model model,
                          @RequestParam("hotelId") Long hotelId,
                          @RequestParam("returnUrl") String returnUrl,
                          @RequestParam("searchText") String searchText,
                          final RedirectAttributes redirectAttributes) {
        List<HotelImageBinding> hotelImageList = new ArrayList<>();

        Hotel hotel = hotelRepository.findOne(hotelId);
        HotelImageBinding hotelImageBinding = new HotelImageBinding();
        hotelImageBinding.setHotelImageMenu((long)0);
        hotelImageBinding.setHotelImageDescription(hotel.getHotelNameEnglish());
        hotelImageBinding.setHotelImageHotelId(hotel.getHotelId());
        hotelImageBinding.setHotelImageRoomId((long)0);
        hotelImageBinding.setHotelImageReturnUrl(returnUrl);
        hotelImageBinding.setHotelImageUrl(hotel.getHotelImageUrl());
        hotelImageList.add(hotelImageBinding);

        List<HotelPicture> hotelPictureList =  hotelPictureRepository.findByhotelPictureHotelWithHotel(hotel);
        for (HotelPicture hotelPicture:hotelPictureList) {
            hotelImageBinding = new HotelImageBinding();
            hotelImageBinding.setHotelImageMenu((long)1);
            hotelImageBinding.setHotelImageDescription(hotelPicture.getHotelPictureHotel().getHotelNameEnglish());
            hotelImageBinding.setHotelImageHotelId(hotelPicture.getHotelPictureHotel().getHotelId());
            hotelImageBinding.setHotelImageRoomId((long)0);
            hotelImageBinding.setHotelImageReturnUrl(returnUrl);
            hotelImageBinding.setHotelImageUrl(hotelPicture.getHotelPicturePictureStore().getPictureStorePath());
            hotelImageList.add(hotelImageBinding);
        }

        List<Room> roomList = roomRepository.findByRoomWithHotel(hotel);
        int iCount ;
        for (Room room: roomList ) {
            iCount=0;
            List<RoomPicture> roomPictureList = roomPictureRepository.findByroomPictureHotelWithHotelAndRoom(hotel,room);
            for (RoomPicture roomPicture:roomPictureList) {
                hotelImageBinding = new HotelImageBinding();
                hotelImageBinding.setHotelImageMenu((long)iCount);
                hotelImageBinding.setHotelImageDescription(roomPicture.getRoomPictureRoom().getRoomRoomType().getRoomTypeNameEnglish());
                hotelImageBinding.setHotelImageHotelId(roomPicture.getRoomPictureHotel().getHotelId());
                hotelImageBinding.setHotelImageRoomId(roomPicture.getRoomPictureRoom().getRoomId());
                hotelImageBinding.setHotelImageReturnUrl(returnUrl);
                hotelImageBinding.setHotelImageUrl(roomPicture.getRoomPicturePictureStore().getPictureStorePath());
                hotelImageList.add(hotelImageBinding);
                iCount++;
            }
        }

        model.addAttribute("hotelImageList", hotelImageList);
        HotelSearchBinding s = new HotelSearchBinding();
        s.setSearchHotelName(searchText);
        model.addAttribute("hotelSearchBinding", s);
        return "frontends/hotelimage";
    }

}
