package Client;

import Server.Mainserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;

//public class Controller_Client implements Initializable {
public class Controller_Client {
    public TextField field;
    public Button startBtn;
    public Label question;
    @FXML
    private AnchorPane anchor;

    //@Override
    //public void initialize(URL location, ResourceBundle resources) {
    public void Start(ActionEvent event) {
        String psw = field.getText();
        //PSW Kontrolle

        question.setText("Die Fragen werden derzeit gesendet");
        anchor.getChildren().remove(startBtn);
        Starten();
    }
    public void Starten() {
        Socket socketServer = null;
        assert false;

        try {
            socketServer = new Socket("localhost", Mainserver.Port);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection");
        }
        System.out.println("Connected");

        DataOutputStream dataOut = null;
        BufferedReader in = null;
        try {

            in = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));
            dataOut = new DataOutputStream(socketServer.getOutputStream());
            //Eingabe-Reader/Ausgabe-Writer erzeugen:


            //Button btn = (Button) fxmlLoader.getNamespace().get("buttonStart");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try{
            String userInput;
            System.out.println(in.readLine());
            //while (in.readLine() != null) {
            //out.println("1234" + "\n");
            //out.flush();
            dataOut.writeBytes("1234" + "\n");
            dataOut.flush();
            System.out.println("Test");
            //System.out.println(in.readLine());
            String read;
            int i=0;
            //while ((read = in.readLine()) != "Ende") {
            while (i!=9) {
                i++;
                System.out.println("hallo");

                do {
                    read = in.readLine();
                    System.out.println("Checkung");
                }while(read == null);
                System.out.println(read);

                if(!read.contains(":") && read.length()>3){
                    read = "1:ende";
                }
                //SetupDisplay(read);
                // sicherheitscheck

                String[] readSplit = read.split(":");



                if (Objects.equals(readSplit[1], "1")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Client_Typ1.fxml"));
                    Parent parent = fxmlLoader.load();
                    Controller_Typ1 dialogController1 = fxmlLoader.<Controller_Typ1>getController();
                    dialogController1.ChangeLabel("Frage nr. " + readSplit[0] + ": " + readSplit[2] + '?');

                    Scene scene = new Scene(parent, 600, 400);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.showAndWait();

                    System.out.println(read + " & " + dialogController1.Answer());
                    System.out.println("Fragentyp1: " + readSplit[0]);
                    //dataOut.writeBytes(readSplit[0] + ":" + dialogController1.Answer() + "\n");
                    dataOut.writeBytes(dialogController1.Answer() + "\n");
                    dataOut.flush();

                } else if (Objects.equals(readSplit[1], "2")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Client_Typ2.fxml"));
                    Parent parent = fxmlLoader.load();
                    Controller_Typ2 dialogController2 = fxmlLoader.<Controller_Typ2>getController();
                    dialogController2.SetMin(Integer.parseInt(readSplit[3]));
                    dialogController2.SetMax(Integer.parseInt(readSplit[4]));
                    dialogController2.ChangeLabel("Frage nr. " + readSplit[0] + ": " + readSplit[2]);

                    Scene scene = new Scene(parent, 600, 400);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.showAndWait();
                    System.out.println(read + " & " + dialogController2.Slide());
                    //dataOut.writeBytes(readSplit[0] + ":" + dialogController2.Slide() + "\n");
                    dataOut.writeBytes(dialogController2.Slide() + "\n");
                    dataOut.flush();
                    System.out.println("Fragentyp2-" + readSplit[0]);

                } else if (Objects.equals(readSplit[1], "3")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Client_Typ3.fxml"));
                    Parent parent = fxmlLoader.load();
                    Controller_Typ3 dialogController3 = fxmlLoader.<Controller_Typ3>getController();
                    dialogController3.ChangeLabel("Frage nr. " + readSplit[0] + ": " + readSplit[2] + '?');

                    Scene scene = new Scene(parent, 600, 400);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.showAndWait();
                    System.out.println(read  + " & " + dialogController3.Text());
                    System.out.println("Fragentyp3" + readSplit[0]);
                    //dataOut.writeBytes(readSplit[0] + ":" + dialogController3.Text() + "\n");
                    dataOut.writeBytes(dialogController3.Text() + "\n");
                    dataOut.flush();
                }else if(Objects.equals(readSplit[1], "ende")){
                    i=10;
                }
            }
            System.out.println("Ende");
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        //}


            /*System.out.println("Test2");
            while ( (textInput = reader.readLine() ) != null && !"e".equals(textInput))
            {

                System.out.println(in.readLine());


            }*/
        //User hat "e" eingegeben: Socket dichtmachen.
        //socketServer.close();
    }

}