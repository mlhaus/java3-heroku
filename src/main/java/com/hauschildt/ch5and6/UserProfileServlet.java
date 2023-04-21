package com.hauschildt.ch5and6;

import com.hauschildt.utilities.MyAzure;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserProfileServlet", value = "/manage-profile")
public class UserProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }
        request.getRequestDispatcher("WEB-INF/ch5/user-profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        MyAzure.sendEmail(
                user.getEmail(),
                "Your test message from my super sweet Java App on Azure",
                "Dear " + user.getFirst_name() + ", This is the test message you requested.");
        request.setAttribute("email_feedback", "<div class=\"alert alert-success\">Please check your email for a test message.</div>");
        request.getRequestDispatcher("WEB-INF/ch5/user-profile.jsp").forward(request, response);
    }
}
