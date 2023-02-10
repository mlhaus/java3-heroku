package com.hauschildt.ch3and4;



import com.hauschildt.utilities.MyTwilio;
import com.hauschildt.utilities.Validators;
import com.twilio.exception.ApiException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "MessageServlet", value = "/messaging")
public class MessageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        String message = request.getParameter("message");
        Map<String,String> results = new HashMap<>();
        boolean validPhone = Validators.isValidUSPhone(phone);
        if(!validPhone) {
            results.put("phoneInvalidMsg", "Invalid phone number");
        }
        results.put("messageInvalidMsg", Validators.isValidMessage(message));
        if(!results.containsKey("phoneInvalidMsg") && results.get("messageInvalidMsg").equals("")) {
            MyTwilio twilio = new MyTwilio();
            results.put("twilioMsg", twilio.sendSMS(phone, message));
        }
        results.put("phone", phone);
        results.put("message", message);
        request.setAttribute("results", results);
        request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
    }
}
