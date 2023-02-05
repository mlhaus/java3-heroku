package com.hauschildt.ch3and4;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class BMIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/bmi.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String height = request.getParameter("height");
        String weight = request.getParameter("weight");
        Map<String,String> results = new HashMap();
        results.put("height", height);
        results.put("weight", weight);
        Person person = new Person();
        try {
            person.setHeightInInches(Double.parseDouble(height));
        } catch(NumberFormatException e) {
            results.put("heightError", "Invalid height");
        }  catch(IllegalArgumentException e) {
            results.put("heightError", e.getMessage());
        }
        try {
            person.setWeightInPounds(Double.parseDouble(weight));
        } catch(NumberFormatException e) {
            results.put("weightError", "Invalid weight");
        } catch(IllegalArgumentException e) {
            results.put("weightError", e.getMessage());
        }

        if(!results.containsKey("heightError") && !results.containsKey("weightError")) {
            String bmi = Double.toString(person.getBMI());
            results.put("bmi", bmi);
        }
        request.setAttribute("results", results);
        request.getRequestDispatcher("WEB-INF/bmi.jsp").forward(request, response);
    }
}
