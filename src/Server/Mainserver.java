package Server;

import Modules.*;
import Modules.SaveToDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.Vector;

public class Mainserver {
    public static int Port = 55555;

    public static void main(String[] args) {

        try {
            getlengthfrdb u = new getlengthfrdb();
            int i = u.getlenngth()+1;
            boolean running = true;
            // Auslesen der Fragen in die Fragen Klasse
            FileReader fr = new FileReader(new File("Fragen.csv"));
            BufferedReader br = new BufferedReader(fr);
            Vector<Frage> Questions = new Vector<>();
            String l = br.readLine();
            while (!l.equals("")) {
                System.out.println(l);
                if (!new getlengthfrdb().getquestions(l.split(":")[2]).equals("null")) {
Questions.add(new Frage(new getlengthfrdb().getquestions(l.split(":")[2]),Integer.valueOf(new getlengthfrdb().getquestions(l.split(":")[2]).split(":")[0])));
                } else {

                        Questions.add(new Frage(l, i));
                        new SaveToDatabase(new Frage(l, i));

                }
                l = br.readLine();
                i++;
            }
            br.close();
            i = 0;



            // Starten des Servers
            ServerSocket b = new ServerSocket(Port);
            Countdown d = new Countdown();
            d.start();
            Clientthread z = new Clientthread(b, Questions, d);
            z.start();
            z.join();
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}