import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;


public class Main_Client extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main_Client.class.getResource("Client.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        launch();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Socket socketServer = null;
        assert false;


        try {
            socketServer = new Socket("localhost", 55555);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection");
        }
        System.out.println("Connected");

        try
        {

            BufferedReader in = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));
            DataOutputStream dataOut = new DataOutputStream(socketServer.getOutputStream());
            //Eingabe-Reader/Ausgabe-Writer erzeugen:

            //Solange der User etwas eingibt (und danach Enter drückt), werden die Daten
            //zum Server geschickt. Eingabe von "X" beendet alles.
            String userInput;
            System.out.println(in.readLine());
            //while (in.readLine() != null) {
            //out.println("1234" + "\n");
            //out.flush();
            dataOut.writeBytes("1234" + "\n");
            dataOut.flush();
            System.out.println("Test");
            String read;
            while((read=in.readLine())!=null){
                String[] readSplit = read.split(":");
                if(Objects.equals(readSplit[1], "1")){
                    System.out.println(read);
                    System.out.println("Fragentyp1" + readSplit[0]);
                    dataOut.writeBytes(readSplit[0]+ ":"+"ja" + "\n");
                    dataOut.flush();
                }else if(Objects.equals(readSplit[1], "2")){
                    System.out.println(read);
                    System.out.println("Fragentyp2-" + readSplit[0]);
                    dataOut.writeBytes(readSplit[0]+ ":"+"1" + "\n");
                    dataOut.flush();
                }else if(Objects.equals(readSplit[1], "3")){
                    System.out.println(read);
                    System.out.println("Fragentyp3" + readSplit[0]);
                    dataOut.writeBytes(readSplit[0]+ ":"+"1.43" + "\n");
                    dataOut.flush();
                }



            }
            dataOut.writeBytes("1" + "\n");
            dataOut.flush();
            System.out.println(in.readLine());

            //}


            /*System.out.println("Test2");
            while ( (textInput = reader.readLine() ) != null && !"e".equals(textInput))
            {

                System.out.println(in.readLine());


            }*/
            //User hat "e" eingegeben: Socket dichtmachen.
            //socketServer.close();
        }
        catch (IOException e)
        {
            System.out.println ("IOException: " + e.getMessage());
            System.exit(-1);
        }
    }
}