<%@ page import="se.michaelthelin.spotify.model_objects.specification.Artist" %>
<%@ page import="org.apache.commons.text.WordUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String q = (String)request.getAttribute("q");
    Artist[] artists = (Artist[])request.getAttribute("artists");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Artists on Spotify</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <div class="container-fluid">
        <a class="navbar-brand" href="artist"><strong>Spotify Music App</strong></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
            </ul>
            <form class="d-flex" role="search" method="get" action="artist">
                <input class="form-control me-2" type="search" placeholder="Search by artist" aria-label="Search" name="q" value="<%= q %>">
                <button class="btn btn-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>

<main class="container">
    <section class="py-5 text-center container">
        <div class="row py-lg-2">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light">Artists</h1>
                <p class="lead text-muted">Click the artist's name below to view their albums</p>
            </div>
        </div>
    </section>

    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th scope="col" style="width: 150px">Picture</th>
            <th scope="col" style="width: 250px">Name</th>
            <th scope="col">Genres</th>
            <th scope="col">Followers</th>
            <th scope="col">Popularity</th>
            <th scope="col">Spotify</th>
        </tr>
        </thead>
        <%--   This is a JSP Comment     --%>
        <!-- This is an HTML Comment -->
        <tbody>
        <% for(Artist artist: artists) { %>
        <tr>
            <th scope="row">
                <% if(artist.getImages().length > 0) { %>
                <img src="<%= artist.getImages()[0].getUrl() %>" width="150" alt="<%= artist.getName() %>">
                <% } else { %>
                <img src="https://picsum.photos/id/56/150/75" alt="<%= artist.getName() %>">
                <% } %>

            </th>
            <td><a href="albums?artistId=<%= artist.getId() %>"><%= artist.getName() %></a></td>
            <td>
                <% for(String genre: artist.getGenres()) { %>
                    <%= WordUtils.capitalize(genre) %><br>
                <% } %>
            </td>

            <td><%= String.format("%,.0f", (double)artist.getFollowers().getTotal()) %></td>
            <td><%= artist.getPopularity() %> of 100</td>
            <td><a class="btn btn-success" href="<%= artist.getExternalUrls().getExternalUrls().get("spotify") %>" target="_blank" role="button">See more</a></td>
        </tr>
        <% } %>
        </tbody>

    </table>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>