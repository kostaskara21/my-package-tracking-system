package com.kara.tracking.system.parsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kara.tracking.system.model.enums.EventTrackingType;


public interface EventPayloadHandler<T>{

   EventTrackingType getTypeOfEvent();

   void handle(T payload) throws JsonProcessingException;

   Class<T> getPayloadType();
}
