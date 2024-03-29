package com.ikubinfo.project.restaurantapp.domain.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
public class ExceptionConstants {
    public static final String USER_NOT_FOUND = "User with id %s not found!";
    public static final String PRODUCT_NOT_FOUND = "Product with id %s not found!";
    public static final String DISH_NOT_FOUND = "Dish with id %s not found!";
    public static final String DRINK_NOT_FOUND = "Drink with id %s not found!";
    public static final String DISH_INGREDIENT_NOT_FOUND = "Ingredient with id %s not found!";
    public static final String ORDER_NOT_FOUND = "Order with id %s not found!";
    public static final String ITEM_NOT_FOUND = "Item with id %s not found!";
    public static final String RECEIPT_NOT_FOUND = "Receipt with id %s not found!";
    public static final String CATEGORY_NOT_FOUND = "Category with id %s not found!";
    public static final String RESERVATION_NOT_FOUND = "Reservation with id %s not found!";
    public static final String TABLE_NOT_FOUND = "Table with id %s not found!";
    public static final String TABLE_RESERVED = "The table is reserved. Please choose another table.";
    public static final String TABLE_TIME_RESERVED = "Not available! Please schedule another time.";
    public static final String USER_NOT_AUTHENTICATED = "You should login to make a reservation";
    public static final String ORDER_PLACED_BEFORE = "Order is placed before. Please create another order.";
    public static final String RESERVATION_FAILED = "Please reserve a table at least 1 hour before the time you want to reserve.";
    public static final String ADD_ITEM_BAD_REQUEST = "Please add one item (a dish or a drink).";
    public static final String MENU_NOT_FOUND = "Menu with id %s not found!";

    public ExceptionConstants(HttpStatus httpStatus, Map<String,String> getRequiredFields){
    }
}
