package com.tjspace.servicebase.Serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class GenderSerialize extends JsonSerializer<Boolean> {
    @Override
    public void serialize(Boolean aBoolean, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(aBoolean!=null){
            if(aBoolean){
                jsonGenerator.writeString("男");
            }else {
                jsonGenerator.writeString("女");
            }
        }else {
            jsonGenerator.writeString("未知");
        }
    }
}
