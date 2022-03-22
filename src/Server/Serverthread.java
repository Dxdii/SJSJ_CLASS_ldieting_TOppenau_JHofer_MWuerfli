package Server;

import Modules.*;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

public class Serverthread extends Thread {
    private Socket socket;
    boolean frunning = true;
    Vector<Frage> d;
    Countdown login;

    public Serverthread(Socket sockets, Vector<Frage> a, Countdown t) {
        this.socket = sockets;
        d = a;
        login = t;
    }

    @Override
    public void run() {

        while (frunning == true) {
            OutputStream sockOut = null;
            try {
                sockOut = socket.getOutputStream();

                BufferedWriter dataOut = new BufferedWriter(new OutputStreamWriter(sockOut));
                BufferedReader sockin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                dataOut.write("Bitte geben sie das Passwort fuer die Umfrage ein" + "\n");
                dataOut.flush();

                String s = sockin.readLine();
                if (s.equals("1234")) {
                    if (login.frunning) {
                        Thread.sleep(login.countdownStarter * 1000 - 3000);
                        dataOut.write("3" + "\n");
                        dataOut.flush();
                        Thread.sleep(1000);
                        dataOut.write("2" + "\n");
                        dataOut.flush();
                        Thread.sleep(1000);
                        dataOut.write("1" + "\n");
                        dataOut.flush();
                        Thread.sleep(1000);
                    }
                    int i = 0;
                    boolean fragezeit = true;

                    while (fragezeit) {
                        Countdown cd = new Countdown();
                        cd.start();

                        // if (Countdown.countdownStarter == 0) {
                        dataOut.write(d.get(i).text + "\n");
                        dataOut.flush();
                        while (cd.frunning) {
                            //   if (Countdown.countdownStarter == 0) {
                            s = sockin.readLine();
                            System.out.println(s);
                            if (cd.countdownStarter * 1000 - 1000 > 0) {
                                Thread.sleep(cd.countdownStarter * 1000 - 1000);
                            } else {
                                Thread.sleep(cd.countdownStarter * 1000);
                            }

                            Thread.sleep(1000);
                        }
                        //Bei Antwort erkennung welcher Fragentyp es ist + Schreibvorgang auf DB
                        if (s != "") {
                            //1Typ
                            if (d.get(i).text.split(":")[1].equals("1")) {
                                System.out.println("erste Frage");
                                if (s.equals("ja")) {
                                    JaNein a = new JaNein(d.get(i).text.split(":")[2], true);
                                    // new SaveToDatabase(a);
                                } else {
                                    JaNein a = new JaNein(s, false);
                                    // new SaveToDatabase(a);
                                }

                                //2Typ
                            } else if (d.get(i).text.split(":")[1].equals("2")) {
                                System.out.println("zweiter Fragentyp");
                                vonBis a = new vonBis(d.get(i).text.split(":")[2], Integer.valueOf(s));
                                // new SaveToDatabase(a);

                                //3Typ
                            } else if (d.get(i).text.split(":")[1].equals("3")) {
                                System.out.println("dritte Fragentyp");
                                Numerisch a = new Numerisch(d.get(i).text.split(":")[1], Float.valueOf(s));
                                // new SaveToDatabase(a);
                            }
                        }

                        //erhoehung für naechste Frage
                        i++;
                        if (i >= d.size()) {
                            fragezeit = false;
                            dataOut.write("Danke fuer ihre Teilnahme" + "\n");
                            dataOut.flush();
                            close();
                        }
                        // }

                        //}


                    }

                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void close() {
        frunning = false;
    }
}
