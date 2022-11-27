package com.tjspace.servicebase.Serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tjspace.utils.commonutils.Const;

import java.io.IOException;

public class AvatarSerialize extends JsonSerializer<String> {

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(Const.AVATAR_PATH + "/" + s);
    }
}
