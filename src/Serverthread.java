import java.net.Socket;

public class Serverthread extends Thread{
    private final Socket socket3;
    boolean frunning = true;
    public Serverthread(Socket sockets) {
        this.socket3 = sockets;
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
