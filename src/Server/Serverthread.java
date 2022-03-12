package Server;

import Modules.Frage;
import Modules.JaNein;
import Modules.Numerisch;
import Modules.vonBis;

import java.io.*;
import java.net.Socket;
import java.util.Vector;

public class Serverthread extends Thread {
    private Socket socket;
    boolean frunning = true;
    Vector<Frage> d;

    public Serverthread(Socket sockets, Vector<Frage> a) {
        this.socket = sockets;
        d = a;
    }

    @Override
    public void run() {

        while (frunning == true) {
            OutputStream sockOut = null;
            try {
                sockOut = socket.getOutputStream();

                DataOutputStream dataOut = new DataOutputStream(sockOut);
                dataOut.writeBytes("Willkommen bitte Melden sie sich zur Umfrage an"+"\n");
                dataOut.flush();

                while (true) {
                    BufferedReader sockin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String s = sockin.readLine();
                    if (s.equals("1234")) {
                        boolean fragezeit = true;
                        int i = 0;
                        while (fragezeit) {

                            // if (Countdown.countdownStarter == 0) {
                            dataOut.writeBytes(d.get(i).text);
                            dataOut.flush();


                            //   if (Countdown.countdownStarter == 0) {

                            s = sockin.readLine();
                            System.out.println(s);
                            if (s != null) {
                                if(d.get(i).text.split(":")[1].equals("1")){
                                    System.out.println("erste Frage");
                                    if(s.equals("Ja")) {
                                        JaNein a = new JaNein(d.get(i).text.split(":")[2],true);
                                        new SaveToDatabase(a);
                                    }else{
                                        JaNein a = new JaNein(s,false);
                                        new SaveToDatabase(a);
                                    }
                                }else if(d.get(i).text.split(":")[1].equals("2")){
                                    System.out.println("zweiter Fragentyp");
                                  vonBis a = new vonBis(d.get(i).text.split(":")[2],Integer.valueOf(s));
                                  new SaveToDatabase(a);
                                }else if(d.get(i).text.split(":")[1].equals("3")){
                                    System.out.println("dritte Fragentyp");
                                    new Numerisch(d.get(i).text.split(":")[1],Float.valueOf(s));
                                }
                            }
                            i++;
                            if (i >= d.size()) {
                                i = 0;
                            }
                            // }

                            //}


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
