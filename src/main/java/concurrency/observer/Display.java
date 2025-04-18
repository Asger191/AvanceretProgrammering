package concurrency.observer;

import concurrency.observersolution.SensorObserver;

public class Display {

    private void displayMessage(int code){
        System.out.println("Hello user. I can inform you that the event " + code + " happened");
    }
}