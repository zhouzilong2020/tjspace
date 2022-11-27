package com.tjspace.servicebase.Serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class DegreeSerialize extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer integer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(integer!=null){
            if(integer==0){
                jsonGenerator.writeString("本科生");
            }else if(integer==1){
                jsonGenerator.writeString("研究生");
            }else {
                jsonGenerator.writeString("博士生");
            }
        }else {
            jsonGenerator.writeString("未知");
        }
    }
}
