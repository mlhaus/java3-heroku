package com.hauschildt.utilities;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import io.github.cdimascio.dotenv.Dotenv;

public class MyTwilio {
private String twilio_phone;
private String twilio_sid;
private String twilio_key;

public MyTwilio() {
    Dotenv dotenv = Dotenv.load();
    twilio_phone = dotenv.get("TWILIO_PHONE");
    twilio_sid = dotenv.get("TWILIO_SID");
    twilio_key = dotenv.get("TWILIO_KEY");
    Twilio.init(twilio_sid, twilio_key);
}

    public String sendSMS(String to, String body) {
        try {
            Message.creator(new PhoneNumber(to),
                    new PhoneNumber(twilio_phone),
                    body).create();
            return "Message Sent";
        } catch(ApiException e) {
            return e.getMessage();
        }


    }
}
