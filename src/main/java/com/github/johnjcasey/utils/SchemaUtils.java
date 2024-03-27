package com.github.johnjcasey.utils;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.coders.Coder;
import org.apache.beam.sdk.schemas.NoSuchSchemaException;
import org.apache.beam.sdk.schemas.SchemaCoder;
import org.apache.beam.sdk.values.TypeDescriptor;


public class SchemaUtils {

    private SchemaUtils() {
    }

    public static <T> Coder<T> getSchemaCoder(Pipeline pipeline, Class<T> clazz) {
        try {
            return SchemaCoder.of(
                    pipeline.getSchemaRegistry().getSchema(clazz),
                    TypeDescriptor.of(clazz),
                    pipeline.getSchemaRegistry().getToRowFunction(clazz),
                    pipeline.getSchemaRegistry().getFromRowFunction(clazz));
        } catch (NoSuchSchemaException e) {
            throw new RuntimeException(e);
        }
    }
}
