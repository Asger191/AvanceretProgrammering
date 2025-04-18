package concurrency.observersolution;

import java.util.ArrayList;
import java.util.List;

class Sensor {
    private List<SensorObserver> observers = new ArrayList<>();

    public void addObserver(SensorObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(SensorObserver observer){
        observers.remove(observer);
    }

    public void eventHappened(int code) {
        for (SensorObserver observer : observers) {
            observer.eventDetected(code);
        }
    }

}