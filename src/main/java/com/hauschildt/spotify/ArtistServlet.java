package com.hauschildt.spotify;

import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ArtistServlet", value = "/artist")
public class ArtistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String q = request.getParameter("q");
        if(q == null) {
            q = "Michael Jackson";
        }
        request.setAttribute("q", q);
        request.setAttribute("artists", MySpotify.getArtists(q));
        request.getRequestDispatcher("WEB-INF/artist.jsp").forward(request, response);
    }
}
