package concurrency.observersolutionwiththreads;

public class Display implements SensorObserver {
    @Override
    public void eventDetected(int code) {
        displayMessage(code);
    }

    private void displayMessage(int code){
        System.out.println("Hello user. I can inform you that the event " + code + " happened");
    }
}
