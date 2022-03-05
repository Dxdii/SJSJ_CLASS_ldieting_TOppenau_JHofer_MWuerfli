import java.io.*;
import java.net.Socket;

public class Serverthread extends Thread {
    private Socket socket;
    boolean frunning = true;

    public Serverthread(Socket sockets) {
        this.socket = sockets;
    }

    @Override
    public void run() {

        while (frunning == true) {
            OutputStream sockOut = null;
            try {
                sockOut = socket.getOutputStream();

                DataOutputStream dataOut = new DataOutputStream(sockOut);
                dataOut.writeBytes("oi");
                dataOut.flush();
                while (true) {

                    BufferedReader sockin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String s = sockin.readLine();
                    if (s.equals("1234")) {
                        Countdown d = new Countdown(dataOut);
                        do {
                            if (d.countdownStarter == 0) {

                                dataOut.writeBytes("Testfrage1");
                                dataOut.flush();
                                d = new Countdown();
                                while (d.countdownStarter == 0) {
                                    s = sockin.readLine();
                                    System.out.println(s);
                                }


                            }
                        } while (d.countdownStarter < 0);
                    } else {
                        socket.close();
                        frunning = false;

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void close() {
        frunning = false;
    }
}
