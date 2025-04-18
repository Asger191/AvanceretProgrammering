package concurrency.observersolution;

public class Alarm implements SensorObserver{


    @Override
    public void eventDetected(int code) {
        if (code > 5)
            soundAlarm();
    }

    private void soundAlarm(){
        System.out.println("WIIIIIIUUUUUUUUU");
    }
}
