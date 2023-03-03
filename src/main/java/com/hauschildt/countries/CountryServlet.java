package com.hauschildt.countries;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CountryServlet", value = "/countries")
public class CountryServlet extends HttpServlet {
    private static List<Country> countries;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(countries == null) {
            countries = CountryDAO_CSV.getAll(request, response);
        }

        String sort = request.getParameter("sort");
        if(sort == null) {
            sort = "";
        }
        switch(sort){
            case "alphaAsc":
                countries.sort((country1, country2) ->  country1.getName().compareTo(country2.getName()));
                break;
            case "alphaDesc":
                countries.sort((country1, country2) ->  country1.getName().compareTo(country2.getName()) * -1);
                break;
            case "populationAsc":
                // Homework
                break;
            case "populationDesc":
                // Homework
                break;
        }

        String show = request.getParameter("show");
        if(show == null) {
            show = "all";
        }
        if(show.equalsIgnoreCase("all")) {
            request.setAttribute("countries", countries);
        } else {
            //https://stackoverflow.com/questions/715650/how-to-clone-arraylist-and-also-clone-its-contents
            List<Country> filteredCountries = new ArrayList<Country>(countries.size());
            for (Country country : countries) {
                try {
                    filteredCountries.add((Country)country.clone());
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
            }
            String finalShow = show;
            filteredCountries.removeIf(country -> !country.getContinent().replaceAll(" ", "").equalsIgnoreCase(finalShow));
            request.setAttribute("countries", filteredCountries);
        }

        request.getRequestDispatcher("WEB-INF/countries/country.jsp").forward(request, response);
    }
}
