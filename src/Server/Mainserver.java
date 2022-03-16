package Server;

import Modules.Frage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Vector;

public class Mainserver {
    public static void main(String[] args) {
        try {

            int i = 0;
            boolean running = true;
// Auslesen der Fragen in die Fragen Klasse
            FileReader fr = new FileReader(new File("Fragen.csv"));
            BufferedReader br = new BufferedReader(fr);
            Vector<Frage> Questions = new Vector<>();
            String l = br.readLine();
            while (l != null) {
                Questions.add(new Frage(l));
                l = br.readLine();
            }
            br.close();


// Starten des Servers
            ServerSocket b = new ServerSocket(55555);
            Countdown d = new Countdown();
            d.start();
            Clientthread z = new Clientthread(b,Questions);
            z.start();
            do {



                if (z.frunning == false) {
                    running = false;
                }

            } while (running == true);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}