import java.util.Timer;
import java.util.TimerTask;


public class Countdown extends Thread{
    public static int countdownStarter = 20;
public boolean frunning = true;
    public Countdown() {
        countdownStarter = 20;
    }
    @Override
    public void run() {
        while(frunning){
        while (countdownStarter  != 0) {
            try {
                Thread.sleep(1000);
              countdownStarter--;
                System.out.println(countdownStarter);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        frunning =false;

    }
    }

}
