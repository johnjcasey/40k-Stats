package com.github.johnjcasey.api.bcp;

import com.github.johnjcasey.data.bcp.Event;
import com.google.gson.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventApi {
    public static final EventApi INSTANCE = new EventApi();
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(org.joda.time.Instant.class, new InstantDeserializer())
            .create();

    private EventApi() {
    }

    public List<Event> get(Instant startDate, Instant endDate) throws IOException, InterruptedException, URISyntaxException {
        List<JsonArray> rawEvents = getRawEvents(startDate, endDate);
        return parseAndFlattenEvents(rawEvents);
    }

    private List<JsonArray> getRawEvents(Instant startDate, Instant endDate) throws IOException, InterruptedException, URISyntaxException {
        List<JsonArray> rawEvents = new ArrayList<>();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://newprod-api.bestcoastpairings.com/v1/events?limit=40&startDate=" + formatter.format(startDate) + "%3A00%3A00Z&endDate=" + formatter.format(endDate) + "%3A00%3A00Z&sortKey=eventDate&sortAscending=true&gameType=1"))
                .header("Client-Id", "259e2q22frfasni9dtjb9q3i7a")
                .build();

        HttpResponse<String> rawResponse = client.send(request, HttpResponse.BodyHandlers.ofString());


        JsonObject response = JsonParser.parseString(rawResponse.body()).getAsJsonObject();
        JsonArray responseData = response.get("data").getAsJsonArray();
        rawEvents.add(responseData);
        while (responseData.size() == 40) {
            String nextKey = response.get("nextKey").getAsJsonPrimitive().getAsString();
            request = HttpRequest.newBuilder()
                    .uri(new URI("https://newprod-api.bestcoastpairings.com/v1/events?limit=40&startDate=" + formatter.format(startDate) + "%3A00%3A00Z&endDate=" + formatter.format(endDate) + "%3A00%3A00Z&sortKey=eventDate&sortAscending=true&gameType=1&nextKey=" + nextKey))
                    .header("Client-Id", "259e2q22frfasni9dtjb9q3i7a")
                    .build();

            rawResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            response = JsonParser.parseString(rawResponse.body()).getAsJsonObject();
            responseData = response.get("data").getAsJsonArray();
            rawEvents.add(responseData);
        }
        return rawEvents;
    }

    private List<Event> parseAndFlattenEvents(List<JsonArray> rawEvents) {
        org.joda.time.Instant now = new org.joda.time.Instant();
        List<Event> events = new ArrayList<>();
        for (JsonArray array : rawEvents) {
            for (JsonElement element : array.asList()) {
                Event event = gson.fromJson(element, Event.class);
                event.queryDate = now;
                event.link = null == event.id ? null : "https://www.bestcoastpairings.com/event/" + event.id;
                events.add(event);
            }
        }
        return events;
    }

    private static class InstantDeserializer implements JsonDeserializer<org.joda.time.Instant> {
        @Override
        public org.joda.time.Instant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return org.joda.time.Instant.parse(json.getAsString());
        }
    }
}
