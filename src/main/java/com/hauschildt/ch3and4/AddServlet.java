package com.hauschildt.ch3and4;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@WebServlet(
        name = "AddServlet"
//        , value = "/add"
        , urlPatterns = {"/add", "/sum", "/addition"}
        , loadOnStartup = -1
)
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/add.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstNumber = request.getParameter("firstNumber");
        String secondNumber = request.getParameter("secondNumber");
        Map<String, String> results = add(firstNumber, secondNumber);
        request.setAttribute("results", results);
        request.getRequestDispatcher("WEB-INF/add.jsp").forward(request,response);
    }

    private Map<String,String> add(String firstNumber, String secondNumber) {
        Map<String, String> results = new HashMap<>();
        boolean firstNumberValid = is_numeric(firstNumber);
        boolean secondNumberValid = is_numeric(secondNumber);
        if(!firstNumberValid){
            results.put("firstNumberError", "Invalid first number");
        }
        if(!secondNumberValid) {
            results.put("secondNumberError", "Invalid second number");
        }
        if(firstNumberValid && secondNumberValid) {
            // TODO Use BigDecimal
            BigDecimal num1 = new BigDecimal(firstNumber);
            BigDecimal num2 = new BigDecimal(secondNumber);
            BigDecimal sum = num1.add(num2);
            results.put("sum", sum.toString());
        }
        results.put("firstNumber", firstNumber);
        results.put("secondNumber", secondNumber);
        return results;
    }

    private boolean is_numeric(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}
