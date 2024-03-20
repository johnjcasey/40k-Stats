package com.github.johnjcasey.api;

import com.github.johnjcasey.data.PlayerAtEvent;
import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class PlayerAtEventApi {

    public static final PlayerAtEventApi INSTANCE = new PlayerAtEventApi();

    private PlayerAtEventApi(){};

    private static final HttpClient client = HttpClient.newHttpClient();

    private static final Gson gson = new Gson();

    public List<PlayerAtEvent> get(String eventId) throws IOException, InterruptedException, URISyntaxException {
        List<JsonArray> rawEvents = getRawPlayers(eventId);
        return parseAndFlattenPlayers(rawEvents);
    }

    private List<JsonArray> getRawPlayers(String eventId) throws IOException, InterruptedException, URISyntaxException{
        List<JsonArray> rawPlayers = new ArrayList<>();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://newprod-api.bestcoastpairings.com/v1/players?limit=100&eventId="+eventId+"&placings=true&expand%5B%5D=team&expand%5B%5D=army&expand%5B%5D=subFaction&expand%5B%5D=character"))
                .header("Client-Id", "259e2q22frfasni9dtjb9q3i7a")
                .build();

        HttpResponse<String> rawResponse = client.send(request, HttpResponse.BodyHandlers.ofString());


        JsonObject response = JsonParser.parseString(rawResponse.body()).getAsJsonObject();
        JsonArray responseData = response.get("data").getAsJsonArray();
        rawPlayers.add(responseData);
        while (responseData.size()==100){
            String nextKey = response.get("nextKey").getAsJsonPrimitive().getAsString();
            request = HttpRequest.newBuilder()
                    .uri(new URI("https://newprod-api.bestcoastpairings.com/v1/players?limit=100&eventId="+eventId+"&placings=true&expand%5B%5D=team&expand%5B%5D=army&expand%5B%5D=subFaction&expand%5B%5D=character&nextKey="+nextKey))
                    .header("Client-Id", "259e2q22frfasni9dtjb9q3i7a")
                    .build();

            rawResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            response = JsonParser.parseString(rawResponse.body()).getAsJsonObject();
            responseData = response.get("data").getAsJsonArray();
            rawPlayers.add(responseData);
        }
        return rawPlayers;
    }

    private List<PlayerAtEvent> parseAndFlattenPlayers(List<JsonArray> rawPlayers){
        List<PlayerAtEvent> events = new ArrayList<>();
        for (JsonArray array : rawPlayers){
            for (JsonElement element :array.asList()){
                events.add(gson.fromJson(element,PlayerAtEvent.class));
            }
        }
        return events;
    }
}
