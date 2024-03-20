package com.github.johnjcasey.api;

import com.github.johnjcasey.data.ArmyList;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class ListApi {

    public static final ListApi INSTANCE = new ListApi();

    private ListApi() {}

    private static final HttpClient client = HttpClient.newHttpClient();

    private static final Gson gson = new Gson();


    public ArmyList get(String listId) throws IOException, InterruptedException, URISyntaxException {
        Properties authprops = new Properties();
        authprops.load(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath()+"bcp.properties"));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://newprod-api.bestcoastpairings.com/v1/armylists/"+ listId))
                .header("Client-Id", "259e2q22frfasni9dtjb9q3i7a")
                .header("Identity", authprops.getProperty("identity"))
                .header("Authorization", authprops.getProperty("authorization"))
                .build();

        HttpResponse<String> rawResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(rawResponse.body(),ArmyList.class);
    }
}
