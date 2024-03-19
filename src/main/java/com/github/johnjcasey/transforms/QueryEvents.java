package com.github.johnjcasey.transforms;

import com.github.johnjcasey.api.EventApi;
import com.github.johnjcasey.data.Event;
import org.apache.beam.io.requestresponse.Caller;
import org.apache.beam.io.requestresponse.RequestResponseIO;
import org.apache.beam.io.requestresponse.UserCodeExecutionException;
import org.apache.beam.sdk.coders.ListCoder;
import org.apache.beam.sdk.coders.RowCoder;
import org.apache.beam.sdk.coders.RowCoderGenerator;
import org.apache.beam.sdk.coders.SerializableCoder;
import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.NoSuchSchemaException;
import org.apache.beam.sdk.schemas.Schema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;
import org.apache.beam.sdk.transforms.Flatten;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.checkerframework.checker.initialization.qual.Initialized;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.UnknownKeyFor;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.List;

import static com.github.johnjcasey.utils.SchemaUtils.getSchemaCoder;

public class QueryEvents extends PTransform<@NonNull PCollection<KV<Instant, Instant>>, @NonNull PCollection<Event>> {

    @NotNull
    @Override
    public PCollection<Event> expand(@NotNull PCollection<KV<Instant, Instant>> input) {
        return input
                .apply(RequestResponseIO.of(new EventApiCaller(), ListCoder.of(getSchemaCoder(input.getPipeline(),Event.class))))
                .getResponses()
                .apply(Flatten.iterables());
    }

    public static class EventApiCaller implements Caller<KV<Instant, Instant>, List<Event>> {
        @Override
        public List<Event> call(KV<Instant, Instant> request) throws @UnknownKeyFor @NonNull @Initialized UserCodeExecutionException {
            try {
                return EventApi.INSTANCE.get(request.getKey(), request.getValue());
            } catch (Exception e) {
                throw new UserCodeExecutionException("Unable to retrieve events", e);
            }
        }
    }
}
