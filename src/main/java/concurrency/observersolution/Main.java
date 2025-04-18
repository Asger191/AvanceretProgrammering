package concurrency.observersolution;

public class Main {

    public static void main(String[] args) {
        Sensor sensor = new Sensor();
        sensor.addObserver(new Logger());
        sensor.addObserver(new Alarm());
        sensor.addObserver(new Display());

        sensor.eventHappened(2);
        sensor.eventHappened(8);
    }
}
