<%@ page import="com.hauschildt.countries.Country" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Country> countries = (List<Country>)request.getAttribute("countries");

%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Where in the World?</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Where in the World?</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
<%--                <ul class="navbar-nav">--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link active" aria-current="page" href="#">Home</a>--%>
<%--                    </li>--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" href="#">About</a>--%>
<%--                    </li>--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" href="#">Contact</a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
            <form class="d-flex">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
            <div class="dropdown ms-3">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="filterDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                    Show
                </button>
                <ul class="dropdown-menu" aria-labelledby="filterDropdown">
                    <li><a class="dropdown-item" href="countries?show=all">All</a></li>
                    <li><a class="dropdown-item" href="countries?show=africa">Africa</a></li>
                    <li><a class="dropdown-item" href="countries?show=asia">Asia</a></li>
                    <li><a class="dropdown-item" href="countries?show=europe">Europe</a></li>
                    <li><a class="dropdown-item" href="countries?show=northamerica">North America</a></li>
                    <li><a class="dropdown-item" href="countries?show=oceania">Oceania</a></li>
                    <li><a class="dropdown-item" href="countries?show=southamerica">South America</a></li>
                </ul>
            </div>
            <div class="dropdown ms-3">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="sortDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                    Sort
                </button>
                <ul class="dropdown-menu" aria-labelledby="sortDropdown">
                    <li><a class="dropdown-item" href="countries?sort=alphaAsc">Alphabetical A-Z</a></li>
                    <li><a class="dropdown-item" href="countries?sort=alphaDesc">Alphabetical Z-A</a></li>
                    <li><a class="dropdown-item" href="countries?sort=populationAsc">Population Low to High</a></li>
                    <li><a class="dropdown-item" href="countries?sort=populationDesc">Population High to Low</a></li>
                </ul>
            </div>
        </div>
    </div>
</nav>

<main class="container my-4">
    <div class="row">
        <% for(Country country: countries) { %>
        <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3">
            <h3><%= country.getName() %></h3>
            <p><%= country.getPopulation() %></p>
        </div>
        <% } %>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>