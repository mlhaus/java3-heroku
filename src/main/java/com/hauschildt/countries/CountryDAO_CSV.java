package com.hauschildt.countries;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountryDAO_CSV {
    public static List<Country> getAll(HttpServletRequest request, HttpServletResponse response) {
        List<Country> countries = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(request.getServletContext().getRealPath("WEB-INF/countries/countries.csv")))) {
            int rowCount = 0;
            while(scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                rowCount++;
                if(rowCount != 1) {
                    String name = line[0];
                    int population = Integer.parseInt(line[5]);
                    String continent = line[2];
                    countries.add(new Country(name, population, continent));
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
        return countries;
    }
}
