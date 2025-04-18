package concurrency.observersolutionwiththreads;

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
            Thread t =  new Thread(() -> {
                try {
                    observer.eventDetected(code);
                } catch (InterruptedException e) {
                    System.out.println("One observer was interrupted: " + observer);
                }
            });
            t.start();
        }
    }

}