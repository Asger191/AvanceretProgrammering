package concurrency.observersolutionwiththreads;

public class Logger implements SensorObserver{


    @Override
    public void eventDetected(int code) throws InterruptedException {
        logEvent(code);
    }

    private void logEvent(int code) throws InterruptedException {
        System.out.println("Logger writes to file: event " + code + " happened at " + System.currentTimeMillis());
        Thread.sleep(2000);
    }
}
