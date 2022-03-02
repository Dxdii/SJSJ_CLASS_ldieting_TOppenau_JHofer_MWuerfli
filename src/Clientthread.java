import java.net.Socket;

public class Clientthread extends Thread {
    private  Socket socket;
    boolean frunning = true;

    public Clientthread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        do {

        } while (frunning);
    }

    public void close() {
        frunning = false;
    }
}
