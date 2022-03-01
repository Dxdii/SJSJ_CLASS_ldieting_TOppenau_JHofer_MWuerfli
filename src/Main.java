import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket a = new ServerSocket(1085);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
