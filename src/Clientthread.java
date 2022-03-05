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
        while(anmeldung){
            try {
                Socket cs = socket.accept();
                Serverthread s = new Serverthread(cs);
                s.start();

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
