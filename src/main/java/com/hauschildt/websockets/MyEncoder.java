package com.hauschildt.websockets;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MyEncoder implements Encoder.Text<MyJson>{

    @Override
    public String encode(MyJson object) throws EncodeException {
        return object.toString();
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
