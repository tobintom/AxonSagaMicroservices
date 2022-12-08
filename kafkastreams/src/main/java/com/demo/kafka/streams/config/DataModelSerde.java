package com.demo.kafka.streams.config;

import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class DataModelSerde extends Serdes.WrapperSerde<DataModel> {
    public DataModelSerde() {
        super(new JsonSerializer<>(), new JsonDeserializer<>(DataModel.class));
    }
}