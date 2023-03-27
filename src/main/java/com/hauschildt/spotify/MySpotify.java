package com.hauschildt.spotify;

import io.github.cdimascio.dotenv.Dotenv;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class MySpotify {
    public static SpotifyApi getSpotifyApi() {
        Dotenv dotenv = Dotenv.load();
        String clientId = dotenv.get("SPOTIFY_CLIENT_ID");
        String clientSecret = dotenv.get("SPOTIFY_CLIENT_SECRET");
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
                .build();
        try {
            final CompletableFuture<ClientCredentials> clientCredentialsFuture = clientCredentialsRequest.executeAsync();
            final ClientCredentials clientCredentials = clientCredentialsFuture.join();

            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
            System.out.println(clientCredentials.getAccessToken());
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
        return spotifyApi;
    }

    public static Artist[] getArtists(String q) {
        SpotifyApi spotifyApi = getSpotifyApi();
        SearchArtistsRequest searchArtistsRequest = spotifyApi.searchArtists(q)
//          .limit(10)
//          .offset(0)
                .build();
        Artist[] artists = null;
        try {
            final CompletableFuture<Paging<Artist>> pagingFuture = searchArtistsRequest.executeAsync();
            final Paging<Artist> artistPaging = pagingFuture.join();

            artists = artistPaging.getItems();
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
        return artists;
    }
}
