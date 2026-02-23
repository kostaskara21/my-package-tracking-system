package com.kara.myauthapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kara.myauthapi.event.TestEvent;

public interface EventPayloadHandler {
    String getType();

   void handle(String payload) throws JsonProcessingException;


}
