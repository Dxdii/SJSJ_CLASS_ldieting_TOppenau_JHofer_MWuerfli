package Server;

import Modules.Frage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Clientthread extends Thread {
    private ServerSocket socket;
    boolean frunning = true;
    boolean anmeldung = true;
    Vector<Frage> e;
    Countdown f;

    public Clientthread(ServerSocket socket, Vector<Frage> a, Countdown d) {
        e = a;
        f = d;
        this.socket = socket;
    }

    public void run() {

        while (frunning) {
      /*      if (Countdown.countdownStarter == 0) {
                anmeldung = false;
            }
            if (anmeldung) {

       */
            if (f.frunning) {
                try {
                    // Akzeptieren der vereinzelten Teilnehmer Multithreading
                    Socket cs = socket.accept();
                    if (f.frunning) {
                        Serverthread s = new Serverthread(cs, e, f);
                        s.start();

                        if (!s.frunning) {
                            close();
                            socket.close();
                        }
                    } else {

                        try {

                            cs = socket.accept();
                            DataOutputStream dataOut = new DataOutputStream(cs.getOutputStream());
                            dataOut.writeBytes("Zu Spaet");
                            dataOut.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

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