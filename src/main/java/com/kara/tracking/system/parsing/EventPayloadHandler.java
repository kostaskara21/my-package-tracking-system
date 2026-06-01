package com.kara.tracking.system.parsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kara.tracking.system.model.enums.EventTrackingType;


public interface EventPayloadHandler<T>{

   //Gives us the type of the Tracking Event in order to pick Strategy
   EventTrackingType getTypeOfEvent();

   void handle(T payload) throws JsonProcessingException;

   //Gives us the Class we will extract the xml to
   Class<T> getPayloadType();
}
