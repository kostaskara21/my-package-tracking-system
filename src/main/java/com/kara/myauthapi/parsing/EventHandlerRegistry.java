package com.kara.myauthapi.parsing;

import com.kara.myauthapi.service.EventPayloadHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.beans.EventHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EventHandlerRegistry {

    private final Map<String, EventPayloadHandler> registry = new HashMap<>();


    public EventHandlerRegistry(List<EventPayloadHandler> handlers) {
        for (EventPayloadHandler handler : handlers) {
            registry.put(handler.getType(), handler);
        }
    }

    public EventPayloadHandler getHandler(String type) {
        EventPayloadHandler handler = registry.get(type);
        if (handler == null) throw new RuntimeException("No handler for type: " + type);
        return handler;
    }

}
