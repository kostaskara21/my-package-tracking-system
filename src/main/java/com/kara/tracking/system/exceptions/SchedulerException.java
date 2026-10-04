package com.kara.tracking.system.exceptions;

public class SchedulerException extends RuntimeException {
    public SchedulerException(String message,Throwable cause) {
      super(message,cause);
    }
}
