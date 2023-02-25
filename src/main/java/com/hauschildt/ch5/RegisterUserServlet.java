package com.hauschildt.ch5;

import com.hauschildt.data_access.UserDAO_MySQL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RegisterUserServlet", value = "/register")
public class RegisterUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/ch5/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String[] agreeToTerms = request.getParameterValues("agree-to-terms");
        Map<String,String> results = new HashMap<>();

        User user = new User();
        try {
            user.setFirst_name(firstName);
        } catch(IllegalArgumentException e) {
            results.put("firstNameError", e.getMessage());
        }
        try {
            user.setLast_name(lastName);
        } catch(IllegalArgumentException e) {
            results.put("lastNameError", e.getMessage());
        }
        try {
            user.setEmail(email);
        } catch(IllegalArgumentException e) {
            results.put("emailError", e.getMessage());
        }
        try {
            user.setPhone(phone);
        } catch(IllegalArgumentException e) {
            results.put("phoneError", e.getMessage());
        }
        try {
            user.setPassword(password1.toCharArray());
        } catch(IllegalArgumentException e) {
            results.put("password1Error", e.getMessage());
        }
        if(password2.equals("")) {
            results.put("password2Error", "This input is required");
        }
        if(!password1.equals(password2)) {
            results.put("password2Error", "Passwords don't match");
        }
        if(agreeToTerms == null || !agreeToTerms[0].equals("agree")){
            results.put("agreeError", "You must agree to our terms and conditions");
        }

        // if there are no errors
        if(!results.containsKey("firstNameError") && !results.containsKey("lastNameError")
                && !results.containsKey("emailError") && !results.containsKey("phoneError")
                && !results.containsKey("password1Error") && !results.containsKey("password2Error")
                && !results.containsKey("agreeError")
        ) {
            UserDAO_MySQL userDAO = new UserDAO_MySQL();
            int numRowsAffected = userDAO.addUser(user);
            if(numRowsAffected == 1) {
//                results.put("userAddSuccess", "Your user account was created");
                response.sendRedirect("validate-user");
            } else {
                results.put("userAddFail", "Your user account could not be created");
            }
        }

        if(!results.containsKey("userAddSuccess")) {
            results.put("firstName", firstName);
            results.put("lastName", lastName);
            results.put("phone", phone);
            results.put("email", email);
            results.put("password1", password1);
            results.put("password2", password2);
            if(agreeToTerms != null && agreeToTerms[0].equals("agree")) {
                results.put("agreeToTerms", agreeToTerms[0]);
            }
        }

        request.setAttribute("results", results);
        request.getRequestDispatcher("WEB-INF/ch5/register.jsp").forward(request, response);
    }
}
