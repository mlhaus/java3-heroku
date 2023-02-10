package com.hauschildt.utilities;

public class Validators {
    public static boolean isValidUSPhone(String phone) {
        return phone.matches("^(1\\s?)?(\\d{3}|\\(\\d{3}\\))[\\s\\-]?\\d{3}[\\s\\-]?\\d{4}$");
    }
    
    public static String isValidMessage(String message) {
        if(message.length() > 160) {
           return "Message cannot be greater than 160 characters";
        }
        if(message.contains("fu**")) {
            return "Naughty word detected";
        }
        return "";
    }
}
