import java.io.IOException;
import java.net.ServerSocket;

public class Mainserver {
    public static void main(String[] args) {
        try {
            ServerSocket a = new ServerSocket(55555);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
