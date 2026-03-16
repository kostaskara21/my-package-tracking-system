package com.kara.tracking.system.parsing;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EventHandlerRegistry {

    private final Map<String, EventPayloadHandler<?>> registry = new HashMap<>();


    public EventHandlerRegistry(List<EventPayloadHandler<?>> handlers) {
        for (EventPayloadHandler handler : handlers) {
            registry.put(handler.getTypeOfEvent(), handler);
        }
    }

    public EventPayloadHandler<?> getHandler(String type) {
        EventPayloadHandler<?> handler = registry.get(type);
        if (handler == null) throw new RuntimeException("No handler for type: " + type);
        return handler;
    }

}
