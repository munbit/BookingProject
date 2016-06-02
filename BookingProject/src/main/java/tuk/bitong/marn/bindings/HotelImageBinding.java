package tuk.bitong.marn.bindings;

import tuk.bitong.marn.domain.Room;

/**
 * Created by MUHAIMAN-HENG on 5/24/2016 AD.
 */
public class HotelImageBinding  {


    private Long hotelImageMenu;
    private Long hotelImageHotelId;
    private Long hotelImageRoomId;
    private String hotelImageDescription;
    private String hotelImageUrl;
    private String hotelImageReturnUrl;

    public HotelImageBinding() {
    }

    public Long getHotelImageMenu() {
        return hotelImageMenu;
    }

    public void setHotelImageMenu(Long hotelImageMenu) {
        this.hotelImageMenu = hotelImageMenu;
    }

    public Long getHotelImageHotelId() {
        return hotelImageHotelId;
    }

    public void setHotelImageHotelId(Long hotelImageHotelId) {
        this.hotelImageHotelId = hotelImageHotelId;
    }

    public Long getHotelImageRoomId() {
        return hotelImageRoomId;
    }

    public void setHotelImageRoomId(Long hotelImageRoomId) {
        this.hotelImageRoomId = hotelImageRoomId;
    }

    public String getHotelImageDescription() {
        return hotelImageDescription;
    }

    public void setHotelImageDescription(String hotelImageDescription) {
        this.hotelImageDescription = hotelImageDescription;
    }

    public String getHotelImageUrl() {
        return hotelImageUrl;
    }

    public void setHotelImageUrl(String hotelImageUrl) {
        this.hotelImageUrl = hotelImageUrl;
    }

    public String getHotelImageReturnUrl() {
        return hotelImageReturnUrl;
    }

    public void setHotelImageReturnUrl(String hotelImageReturnUrl) {
        this.hotelImageReturnUrl = hotelImageReturnUrl;
    }
}
