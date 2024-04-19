package com.github.johnjcasey.transforms.bcp;

import com.github.johnjcasey.api.bcp.ListApi;
import com.github.johnjcasey.data.bcp.*;
import org.apache.beam.io.requestresponse.Caller;
import org.apache.beam.io.requestresponse.RequestResponseIO;
import org.apache.beam.io.requestresponse.UserCodeExecutionException;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.checkerframework.checker.initialization.qual.Initialized;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.UnknownKeyFor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.github.johnjcasey.utils.SchemaUtils.getSchemaCoder;


public class QueryLists extends PTransform<@NonNull PCollection<KV<Event, List<PlayerAtEvent>>>, @NonNull PCollection<EventWithPlayersAndLists>> {
    @NotNull
    @Override
    public PCollection<EventWithPlayersAndLists> expand(@NotNull PCollection<KV<Event, List<PlayerAtEvent>>> input) {
        return input.apply(RequestResponseIO.of(new ListApiCaller(), getSchemaCoder(input.getPipeline(), EventWithPlayersAndLists.class)))
                .getResponses();
    }

    public static class ListApiCaller implements Caller<KV<Event, List<PlayerAtEvent>>, EventWithPlayersAndLists> {
        @Override
        public EventWithPlayersAndLists call(KV<Event, List<PlayerAtEvent>> request) throws @UnknownKeyFor @NonNull @Initialized UserCodeExecutionException {
            EventWithPlayersAndLists epl = new EventWithPlayersAndLists();
            epl.event = request.getKey();
            epl.playersWithList = new ArrayList<>();
            for (PlayerAtEvent player : request.getValue()) {
                if (null != player.armyListObjectId) {
                    try {
                        PlayerAndList pal = new PlayerAndList();
                        pal.player = player;
                        pal.list = ListApi.INSTANCE.get(player.armyListObjectId);
                        epl.playersWithList.add(pal);
                    } catch (Exception e) {
                        throw new UserCodeExecutionException("Unable to retrieve Lists", e);
                    }
                }
            }
            return epl;
        }
    }
}
