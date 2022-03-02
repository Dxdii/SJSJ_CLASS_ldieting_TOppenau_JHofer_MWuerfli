import java.net.Socket;

public class Serverthread extends Thread{
    private  Socket socket;
    boolean frunning = true;
    public Serverthread(Socket sockets) {
        this.socket = sockets;
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
