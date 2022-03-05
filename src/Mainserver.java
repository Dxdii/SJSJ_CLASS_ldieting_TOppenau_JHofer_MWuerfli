import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Mainserver {
    public static void main(String[] args) {
        try {
            String s;
            int i = 0;
            boolean running = true;
            ServerSocket a = new ServerSocket(1085);
            do {

                Clientthread z = new Clientthread(a);
                z.start();
                if (z.frunning == false) {
                    running = false;
                }
            } while (running == true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

