package com.ikubinfo.project.restaurantapp.domain;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
public class Constants {

    public static final String WINNER_EMAIL_SUBJECT = "Congrats! You won a free dinner!";
    public static final String WINNER_EMAIL = "\nAs a thank you for your loyalty, we want to inform you that you just won a free dinner for 2 people at our "+
            "restaurant. Please confirm or contact us at our mobile number for further information." +
            "\nNote: This offer is valid for one week." +
            "\n\nBest Regards, \nRestaurantTeam";

    public static final String GUEST_EMAIL_ADDRESS = "guest@gmail.com";
    public static final String ADMIN_EMAIL_ADDRES = "xh.ahmetaj22@gmail.com";

    public static final String WARNING_EMAIL_SUBJECT = "Warning! Check low quantity products.!!";
    public static final String EMAIL_CONFIRMATION_SUBJECT = "Reservation Confirmed!";
    public static final String EMAIL_CONFIRMATION_BODY = "Dear %s ! \nYour reservation at our restaurant is confirmed! " +
            "\nReservation Details:"+
            "\nName: %s %s" +
            "\nDate: %s" +
            "\nTime: %s" +
            "\nNumber of People: %s" +
            "\nTable: Nr %s"+
            "\n\nWe hope you have a great time!"+
            "\n\nAll the best, \nReservationTeam";

    public static final String BIRTHDAY_EMAIL_SUBJECT = "Make a wish & unlock the email!";
    public static final String BIRTHDAY_EMAIL_BODY = "\t:) Happy birthday %s! :) \n\nOn the occasion of your birthday, we wish you all the happiness that the room in your heart can hold. \n" +
            "What better time for a special gift? \tUse this 50%% discount for an amazing dinner with your beloved ones." +
            "\n\nPlease send a confirmation message if you want to come.\nMay you have a wonderful day!"+
            "\n\nAll the best, \nReservationTeam";

    public static final String ITEM_REMOVED = "Item with id %s removed.";
    public static final String STATUS_CHANGED = "The order status is changed to %s.";
    public static final String DRINK_DELETED = "Successfully deleted drink with id %s.";

    public Constants(HttpStatus httpStatus, Map<String,String> getRequiredFields){
    }
}
