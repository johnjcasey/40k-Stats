package com.github.johnjcasey.transforms;

import com.github.johnjcasey.api.EventApi;
import com.github.johnjcasey.data.Event;
import org.apache.beam.io.requestresponse.Caller;
import org.apache.beam.io.requestresponse.RequestResponseIO;
import org.apache.beam.io.requestresponse.UserCodeExecutionException;
import org.apache.beam.sdk.coders.ListCoder;
import org.apache.beam.sdk.coders.SerializableCoder;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.Flatten;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptor;
import org.apache.beam.sdk.values.TypeDescriptors;
import org.checkerframework.checker.initialization.qual.Initialized;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.UnknownKeyFor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

public class QueryEvents extends PTransform<@NonNull PCollection<byte[]>, @NonNull PCollection<Event>> {

    @NotNull
    @Override
    public PCollection<Event> expand(@NotNull PCollection<byte[]> input) {
        return input.apply(ParDo.of(new DoFn<byte[], DateRange>() {
                    @ProcessElement
                    public void processElement(OutputReceiver<DateRange> outputReceiver) {
                        outputReceiver.output(new DateRange(Instant.now().minus(7, ChronoUnit.DAYS), Instant.now()));
                    }
                }))
                .apply(RequestResponseIO.of(new EventApiCaller(), ListCoder.of(SerializableCoder.of(Event.class))))
                .getResponses()
                .apply(Flatten.iterables());
    }

    public static class EventApiCaller implements Caller<DateRange, List<Event>> {
        @Override
        public List<Event> call(DateRange request) throws @UnknownKeyFor @NonNull @Initialized UserCodeExecutionException {
            try {
                return EventApi.INSTANCE.get(request.startDate, request.endDate);
            } catch (Exception e) {
                throw new UserCodeExecutionException("Unable to retrieve events", e);
            }
        }
    }

    public static class DateRange implements Serializable {

        public DateRange(Instant startDate, Instant endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Instant startDate;
        public Instant endDate;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DateRange dateRange = (DateRange) o;
            return Objects.equals(startDate, dateRange.startDate) && Objects.equals(endDate, dateRange.endDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate);
        }
    }
}
