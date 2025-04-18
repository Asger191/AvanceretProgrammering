package concurrency.observersolution;

public class Logger implements SensorObserver{


    @Override
    public void eventDetected(int code) {
        logEvent(code);
    }

    private void logEvent(int code){
        System.out.println("Logger writes to file: event " + code + " happened at " + System.currentTimeMillis());
    }
}
