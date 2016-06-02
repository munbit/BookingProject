package tuk.bitong.marn.bindings;

import tuk.bitong.marn.domain.Hotel;

import java.util.Date;

/**
 * Created by MUHAIMAN-HENG on 5/24/2016 AD.
 */
public class HotelSearchBinding {
    private String searchHotelName;
    private String searchStartDate;
    private String searchEndDate;
    private int searchRoomQuantity;
    private int searchAdulQuantity;
    private int searchBabyQuantity;

    public HotelSearchBinding() {
    }

    public String getSearchHotelName() {
        return searchHotelName;
    }

    public void setSearchHotelName(String searchHotelName) {
        this.searchHotelName = searchHotelName;
    }

    public String getSearchStartDate() {
        return searchStartDate;
    }

    public void setSearchStartDate(String searchStartDate) {
        this.searchStartDate = searchStartDate;
    }

    public String getSearchEndDate() {
        return searchEndDate;
    }

    public void setSearchEndDate(String searchEndDate) {
        this.searchEndDate = searchEndDate;
    }

    public int getSearchRoomQuantity() {
        return searchRoomQuantity;
    }

    public void setSearchRoomQuantity(int searchRoomQuantity) {
        this.searchRoomQuantity = searchRoomQuantity;
    }

    public int getSearchAdulQuantity() {
        return searchAdulQuantity;
    }

    public void setSearchAdulQuantity(int searchAdulQuantity) {
        this.searchAdulQuantity = searchAdulQuantity;
    }

    public int getSearchBabyQuantity() {
        return searchBabyQuantity;
    }

    public void setSearchBabyQuantity(int searchBabyQuantity) {
        this.searchBabyQuantity = searchBabyQuantity;
    }
}
