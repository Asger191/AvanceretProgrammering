package concurrency.observer;

import concurrency.observersolution.SensorObserver;

public class Logger{

    private void logEvent(int code){
        System.out.println("Logger writes to file: event " + code + " happened at " + System.currentTimeMillis());
    }
}