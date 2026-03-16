package com.kara.tracking.system.process;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.kara.tracking.system.event.TestEvent;
import com.kara.tracking.system.model.enums.EventTrackingType;
import com.kara.tracking.system.parsing.EventHandlerRegistry;
import com.kara.tracking.system.parsing.EventPayloadHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventProcessor {

    private final EventHandlerRegistry registry;
    private  final XmlMapper xmlMapper;

    public <T> void process(TestEvent event) throws JsonProcessingException {

        /*EventPayloadHandler<?> mysteryHandler = registry.getHandler(event.getType());
*/

        EventPayloadHandler<T> handler = getEventHandler(event);

        //Class<T> targetClass = handler.getPayloadType();

        T typedPayload = extractPayload(event.getPayload(), handler.getPayloadType());

        handler.handle(typedPayload);
        /*registry.getHandler(event.getType())
                .handle(event.getPayload());*/
    }


    private <T> T extractPayload(String payload,Class<T> clazz) throws JsonProcessingException {
        try {
            return xmlMapper.readValue(payload, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Mapping failed for " + clazz.getSimpleName(), e);
        }

    }

    private <T> EventPayloadHandler<T> getEventHandler(TestEvent event) {
        return  (EventPayloadHandler<T>) registry.getHandler(String.valueOf(EventTrackingType.valueOf(event.getType())));

    }
}