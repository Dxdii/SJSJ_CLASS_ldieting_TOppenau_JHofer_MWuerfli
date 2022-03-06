import java.util.Timer;
import java.util.TimerTask;


public class Countdown {
    public static int countdownStarter = 20;

    public Countdown() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(countdownStarter);
                if (countdownStarter > 0)
                    countdownStarter--;

                if (countdownStarter == 0)
                    System.exit(0);
            }
        };
        timer.schedule(task, 0, 1000);
    }

}
