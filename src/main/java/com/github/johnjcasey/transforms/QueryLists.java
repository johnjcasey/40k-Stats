package com.github.johnjcasey.transforms;

import com.github.johnjcasey.api.ListApi;
import com.github.johnjcasey.data.ArmyList;
import org.apache.beam.io.requestresponse.Caller;
import org.apache.beam.io.requestresponse.RequestResponseIO;
import org.apache.beam.io.requestresponse.UserCodeExecutionException;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.values.PCollection;
import org.checkerframework.checker.initialization.qual.Initialized;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.UnknownKeyFor;
import org.jetbrains.annotations.NotNull;

import static com.github.johnjcasey.utils.SchemaUtils.getSchemaCoder;


public class QueryLists extends PTransform<@NonNull PCollection<String>, @NonNull PCollection<ArmyList>> {
    @NotNull
    @Override
    public PCollection<ArmyList> expand(@NotNull PCollection<String> input) {
        return input.apply(RequestResponseIO.of(new ListApiCaller(), getSchemaCoder(input.getPipeline(), ArmyList.class)))
                .getResponses();
    }

    public static class ListApiCaller implements Caller<String, ArmyList> {
        @Override
        public ArmyList call(String request) throws @UnknownKeyFor @NonNull @Initialized UserCodeExecutionException {
            try {
                return ListApi.INSTANCE.get(request);
            } catch (Exception e) {
                throw new UserCodeExecutionException("Unable to retrieve Lists", e);
            }
        }
    }
}
