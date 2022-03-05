import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Countdown {
    public int countdownStarter = 20;

    public Countdown(DataOutputStream dataOut) {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {


            public void run() {
                System.out.println(countdownStarter);
                countdownStarter--;
                if (countdownStarter < 4 && countdownStarter >= 0) {
                    try {
                        dataOut.writeBytes(String.valueOf(countdownStarter));
                        dataOut.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (countdownStarter < 0) {
                    System.out.println("Timer Over!");
                    scheduler.shutdown();
                }
            }

        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }
}
