package com.kara.tracking.system.parsing;

import com.fasterxml.jackson.core.JsonProcessingException;


 public interface EventPayloadHandler<T>{

   String getTypeOfEvent();

   void handle(T payload) throws JsonProcessingException;

   Class<T> getPayloadType();
}
