import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Clientthread extends Thread {
    private ServerSocket socket;
    boolean frunning = true;
    boolean anmeldung = true;

    public Clientthread(ServerSocket socket) {

        this.socket = socket;
    }

    public void run() {

        while (frunning) {
        if(Countdown.countdownStarter==0){
            anmeldung = false;
        }
        if(anmeldung){
            try {

                Socket cs = socket.accept();
                Serverthread s = new Serverthread(cs);
                s.start();


            } catch (IOException e) {
                e.printStackTrace();
            }


        }else{
            try {

                Socket cs = socket.accept();
                DataOutputStream dataOut = new DataOutputStream(cs.getOutputStream());
                dataOut.writeBytes("Zu Spaet");
                dataOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        }

    }

    public void close() {
        frunning = false;
    }
}
