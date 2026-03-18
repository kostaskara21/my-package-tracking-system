package com.kara.tracking.system.process;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.kara.tracking.system.event.TrackingEvent;
import com.kara.tracking.system.model.enums.EventTrackingType;
import com.kara.tracking.system.parsing.EventHandlerRegistry;
import com.kara.tracking.system.parsing.EventPayloadHandler;
import com.kara.tracking.system.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventProcessor {

    private final EventHandlerRegistry registry;
    private  final XmlMapper xmlMapper;
    private final AuthUserService authUserService;

    public <T> void process(TrackingEvent event) throws JsonProcessingException {


        String uid= event.getAuthId();

        authUserService.authenticateUser(uid).block();

        EventPayloadHandler<T> handler = getEventHandler(event);

        T typedPayload = extractPayload(event.getPayload(), handler.getPayloadType());

        handler.handle(typedPayload);
    }


    private <T> T extractPayload(String payload,Class<T> clazz) throws JsonProcessingException {
        try {
            return xmlMapper.readValue(payload, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Mapping failed for " + clazz.getSimpleName(), e);
        }

    }

    private <T> EventPayloadHandler<T> getEventHandler(TrackingEvent event) {
        return  (EventPayloadHandler<T>) registry.getHandler(String.valueOf(EventTrackingType.valueOf(event.getType())));

    }
}