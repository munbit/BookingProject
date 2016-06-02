package tuk.bitong.marn.bindings;

import tuk.bitong.marn.domain.Hotel;
import tuk.bitong.marn.domain.HotelPicture;

import java.util.List;

/**
 * Created by MUHAIMAN-HENG on 5/24/2016 AD.
 */
public class HotelExtraBinding extends Hotel {

    private List<HotelPicture> hotelHotelPictureList;

    public HotelExtraBinding() {
    }

    public HotelExtraBinding(Hotel hotel) {
        this.hotelId = hotel.getHotelId();
        this.hotelNameThai = hotel.getHotelNameThai();
        this.hotelNameEnglish = hotel.getHotelNameEnglish();
        this.hotelDescriptionThai = hotel.getHotelDescriptionThai();
        this.hotelDescriptionEnglish = hotel.getHotelDescriptionEnglish();
        this.hotelAddressThai = hotel.getHotelAddressThai();
        this.hotelAddressEnglish = hotel.getHotelAddressEnglish();
        this.hotelLocality = hotel.getHotelLocality();
        this.hotelHotelLevel = hotel.getHotelHotelLevel();
        this.hotelHotelType = hotel.getHotelHotelType();
        this.hotelImageUrl = hotel.getHotelImageUrl();
        this.enabled = hotel.getEnabled();
    }

    public List<HotelPicture> getHotelHotelPictureList() {
        return hotelHotelPictureList;
    }

    public void setHotelHotelPictureList(List<HotelPicture> hotelHotelPictureList) {
        this.hotelHotelPictureList = hotelHotelPictureList;
    }
}
