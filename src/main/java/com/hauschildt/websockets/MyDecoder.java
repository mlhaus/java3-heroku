package com.hauschildt.websockets;

import jakarta.json.Json;
import jakarta.json.JsonException;
import jakarta.json.JsonObject;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;

public class MyDecoder implements Decoder.Text<MyJson> {

    @Override
    public MyJson decode(String s) throws DecodeException {
        JsonObject jsonObject = Json.createReader(new StringReader(s)).readObject();
        return new MyJson(jsonObject);
    }

    @Override
    public boolean willDecode(String s) {
        boolean result;
        try {
            JsonObject jsonObject = Json.createReader(new StringReader(s)).readObject();
            result = true;
        } catch (JsonException jex) {
            result = false;
        }
        return result;
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
