package concurrency.observersolutionwiththreads;

public interface SensorObserver {
    void eventDetected(int code) throws InterruptedException;
}