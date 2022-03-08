import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;

public class Mainserver {
    public static void main(String[] args) {
        try {

            int i = 0;
            boolean running = true;

            FileReader fr = new FileReader(new File("Fragen.csv"));
            BufferedReader br = new BufferedReader(fr);
            String l = br.readLine();
            l = br.readLine();
            while (l != null) {
                i++;
                l = br.readLine();
            }
           br.close();
            br = new BufferedReader(new FileReader(new File("Fragen.csv")));
            Frage[] a = new Frage[i];
            l = br.readLine();
            i=0;
            while (l != null) {
               a[i] = new Frage(l);
                System.out.println(l);
                l = br.readLine();
            }


            ServerSocket b = new ServerSocket(55555);
            Countdown d = new Countdown();
            d.start();
            Clientthread z = new Clientthread(b);
            z.start();

            do {

                if (z.frunning == false) {
                    running = false;
                }

            } while (running == true);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}