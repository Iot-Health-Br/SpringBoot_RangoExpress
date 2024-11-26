package com.SpringBoot_RangoExpress.Exception;

public class OrderWasRegistred extends RuntimeException {
    public OrderWasRegistred(String message) {
        super(message);
    }
}
