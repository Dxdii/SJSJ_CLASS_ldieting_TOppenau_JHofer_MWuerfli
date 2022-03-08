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
                dataOut.writeBytes("Willkommen bitte Melden sie sich zur Umfrage an");
                dataOut.flush();

                while (true) {
                    BufferedReader sockin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String s = sockin.readLine();
                    if (s.equals("1234")) {
                        boolean fragezeit = true;
                       while(fragezeit){

                           if(Countdown.countdownStarter ==0){
                               dataOut.writeBytes("Testfrage 1");
                               dataOut.flush();

                               if(Countdown.countdownStarter==0){

                                 s = sockin.readLine();
                                   System.out.println(s);


                               }

                           }


                       }
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
