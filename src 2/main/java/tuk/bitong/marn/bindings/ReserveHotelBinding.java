package tuk.bitong.marn.bindings;

import java.util.List;
import tuk.bitong.marn.domain.Hotel;
import tuk.bitong.marn.domain.Room;

/**
 * Created by MUHAIMAN-HENG on 5/24/2016 AD.
 */
public class ReserveHotelBinding {
    private Hotel hotel;
    private String searchStartDate;
    private String searchEndDate;
    private int searchRoomQuantity;
    private int searchAdulQuantity;
    private int searchBabyQuantity;
    private Double reserveTotalAmount;
    private List<ReserveRoomBinding> reserveRoomList;

    public ReserveHotelBinding() {
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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

    public Double getReserveTotalAmount() {
        return reserveTotalAmount;
    }

    public void setReserveTotalAmount(Double reserveTotalAmount) {
        this.reserveTotalAmount = reserveTotalAmount;
    }

    public List<ReserveRoomBinding> getReserveRoomList() {
        return reserveRoomList;
    }

    public void setReserveRoomList(List<ReserveRoomBinding> reserveRoomList) {
        this.reserveRoomList = reserveRoomList;
    }
}
